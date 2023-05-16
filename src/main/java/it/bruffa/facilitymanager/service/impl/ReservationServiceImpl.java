package it.bruffa.facilitymanager.service.impl;

import it.bruffa.facilitymanager.model.dto.ReservationFilter;
import it.bruffa.facilitymanager.model.dto.request.CreateReservationRequest;
import it.bruffa.facilitymanager.model.entity.Reservation;
import it.bruffa.facilitymanager.model.entity.ReservationBuilder;
import it.bruffa.facilitymanager.model.entity.Structure;
import it.bruffa.facilitymanager.repository.ReservationRepository;
import it.bruffa.facilitymanager.repository.StructureRepository;
import it.bruffa.facilitymanager.service.AccessLogService;
import it.bruffa.facilitymanager.service.ReservationService;
import it.bruffa.facilitymanager.service.StructureService;
import it.bruffa.facilitymanager.utilities.PropertiesHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private StructureRepository structureRepository;
    @Autowired
    private StructureServiceImpl structureService;
    private static final Logger logger = LoggerFactory.getLogger(ReservationServiceImpl.class);

    public List<Reservation> getReservationsWithCheckOnDay(LocalDate checkOutDate) {

        List<Reservation> reservations = reservationRepository.findAllByCheckOut(checkOutDate);

        return reservations;
    }

    @Override
    public ResponseEntity<Page<Reservation>> filter(ReservationFilter probe, Integer page, Integer size, String sortField, String sortDirection) {
        try {
            logger.debug("filter() called with probe: {}, page: {}, size: {}, sortField: {}, sortDirection: {}", probe, page, size, sortField, sortDirection);
            Pageable pageable;

            Reservation filter = new Reservation();

            if (probe.getStructureId() != null && structureRepository.existsById(probe.getStructureId()))
                filter.setStructure(structureRepository.findById(probe.getStructureId()).get());

            PropertiesHelper.copyNonNullProperties(probe, filter);


            if (org.springframework.util.StringUtils.isEmpty(sortField)) {
                pageable = PageRequest.of(page, size);
            } else {

                Sort.Direction dir = StringUtils.isEmpty(sortDirection) ? Sort.Direction.ASC : Sort.Direction.valueOf(sortDirection.trim().toUpperCase());
                pageable = PageRequest.of(page, size, dir, sortField);
            }

            ExampleMatcher matcher = ExampleMatcher.matchingAll().withIgnoreCase().withIgnoreNullValues().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
            Example<Reservation> example = Example.of(filter, matcher);

            return ResponseEntity.ok(reservationRepository.findAll(example, pageable));
        } catch (Exception e) {
            logger.error("filter() failed with exception: {}", e.getMessage());
            return ResponseEntity.status(500).build();
        }
    }

    @Override
    public ResponseEntity<Reservation> getReservationById(Long reservationId) {
        try {
            logger.debug("getReservationById() called with reservationId: {}", reservationId);
            return ResponseEntity.ok(reservationRepository.findById(reservationId).orElseThrow(() -> new Exception("Reservation not found")));
        } catch (Exception e) {
            logger.error("getReservationById() failed with exception: {}", e.getMessage());
            return ResponseEntity.status(404).build();
        }
    }

    @Override
    public ResponseEntity<Reservation> createReservation(CreateReservationRequest createReservationRequest) {
        try {
            logger.debug("createReservation() called with createReservationRequest: {}", createReservationRequest);
            Structure structure = structureRepository.findById(createReservationRequest.getStructureId()).orElseThrow(  () -> new Exception("Structure not found"));
            Reservation reservation = ReservationBuilder.builder().structure(structure).arrival(createReservationRequest.getArrival()).departure(createReservationRequest.getDeparture()).checkIn(createReservationRequest.getCheckIn()).checkOut(createReservationRequest.getCheckOut()).guests(createReservationRequest.getGuests()).guestSurname(createReservationRequest.getGuestSurname()).guestName(createReservationRequest.getGuestName()).build();

            List<Reservation> reservations = reservationRepository.getAllBetweenDatesAndStructure(createReservationRequest.getArrival(), createReservationRequest.getDeparture(), createReservationRequest.getStructureId());

            if (reservations.size() > 0) throw new Exception("There is already a reservation in the selected period");

            return ResponseEntity.ok(reservationRepository.save(reservation));

        } catch (Exception e) {
            logger.error("createReservation() failed with exception: {}", e.getMessage());
            return ResponseEntity.status(500).build();
        }

    }

    @Override
    public ResponseEntity<Boolean> deleteReservation(Long reservationId) {
        try {
            logger.debug("deleteReservation() called with reservationId: {}", reservationId);
            reservationRepository.deleteById(reservationId);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            logger.error("deleteReservation() failed with exception: {}", e.getMessage());
            return ResponseEntity.status(500).build();
        }
    }
}
