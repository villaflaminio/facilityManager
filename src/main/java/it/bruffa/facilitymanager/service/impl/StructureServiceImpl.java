package it.bruffa.facilitymanager.service.impl;

import it.bruffa.facilitymanager.model.builder.CleaningActionBuilder;
import it.bruffa.facilitymanager.model.builder.StructureBuilder;
import it.bruffa.facilitymanager.model.dto.StructureFilter;
import it.bruffa.facilitymanager.model.dto.request.CreateStructureRequest;
import it.bruffa.facilitymanager.model.entity.*;
import it.bruffa.facilitymanager.model.projection.StructureIdInfo;
import it.bruffa.facilitymanager.model.projection.StructureInfo;
import it.bruffa.facilitymanager.repository.GateRepository;
import it.bruffa.facilitymanager.repository.QuoteRepository;
import it.bruffa.facilitymanager.repository.ReservationRepository;
import it.bruffa.facilitymanager.repository.StructureRepository;
import it.bruffa.facilitymanager.service.CleaningActionService;
import it.bruffa.facilitymanager.service.StructureService;
import it.bruffa.facilitymanager.utilities.PropertiesHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.List;

@Service
public class StructureServiceImpl implements StructureService {
    private static final Logger logger = LoggerFactory.getLogger(StructureService.class);

    @Autowired
    private StructureRepository structureRepository;
    @Autowired
    private GateRepository gateRepository;
    @Autowired
    private QuoteRepository quoteRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public ResponseEntity<Structure> createStructure(CreateStructureRequest createStructureRequest) {

        try {
            logger.info("createStructure() called with createStructure: {}", createStructureRequest);

            Structure structure = StructureBuilder.builder()
                    .name(createStructureRequest.getName())
                    .address(createStructureRequest.getAddress())
                    .city(createStructureRequest.getCity())
                    .cap(createStructureRequest.getCap())
                    .province(createStructureRequest.getProvince())
                    .country(createStructureRequest.getCountry())
                    .latitude(createStructureRequest.getLatitude())
                    .longitude(createStructureRequest.getLongitude())
                    .description(createStructureRequest.getDescription())
                    .isActive(createStructureRequest.getIsActive())
                    .cleaningDuration(createStructureRequest.getCleaningDuration())
                    .build();

            logger.info("Structure created: {}", structure);

            return ResponseEntity.ok(structureRepository.save(structure));

        } catch (Exception e) {
            logger.error("Error in createStructure() method: {}", e.getMessage());
            throw e;
        } finally {
            logger.debug("Exit from createStructure() method");
        }
    }

    @Override
    public ResponseEntity<Page<Structure>> filter(StructureFilter probe, Integer page, Integer size, String sortField, String sortDirection) {
        try {
            logger.debug("filter() called with probe: {}, page: {}, size: {}, sortField: {}, sortDirection: {}", probe, page, size, sortField, sortDirection);
            Pageable pageable;

            Structure filter = new Structure();

            if (probe.getGateId() != null && gateRepository.existsById(probe.getGateId()))
                filter.setGate(gateRepository.findById(probe.getGateId()).orElseThrow(() -> new RuntimeException("Gate not found")));


            if (probe.getQuoteId() != null && quoteRepository.existsById(probe.getQuoteId()))
                filter.setQuote(quoteRepository.findById(probe.getQuoteId()).orElseThrow(() -> new RuntimeException("Quote not found")));

            PropertiesHelper.copyNonNullProperties(probe, filter);


            if (org.springframework.util.StringUtils.isEmpty(sortField)) {
                pageable = PageRequest.of(page, size);
            } else {

                Sort.Direction dir = StringUtils.isEmpty(sortDirection) ? Sort.Direction.ASC : Sort.Direction.valueOf(sortDirection.trim().toUpperCase());
                pageable = PageRequest.of(page, size, dir, sortField);
            }

            ExampleMatcher matcher = ExampleMatcher.matchingAll().withIgnoreCase().withIgnoreNullValues().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
            Example<Structure> example = Example.of(filter, matcher);

            return ResponseEntity.ok(structureRepository.findAll(example, pageable));
        } catch (Exception e) {
            logger.error("Error in filter() method: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public ResponseEntity<StructureInfo> getStructureById(Long structureId) {
        try {
            logger.debug("getStructureById() called with structureId: {}", structureId);
            StructureInfo structure = structureRepository.findStructureById(structureId).orElseThrow(() -> new RuntimeException("Structure not found"));
            return ResponseEntity.ok(structure);
        } catch (Exception e) {
            logger.error("Error in getStructureById() method: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public ResponseEntity<List<StructureIdInfo>> getStructuresList() {
        try {
            logger.debug("getStructuresList() called");
            return ResponseEntity.ok(structureRepository.getStructureIdInfoBy());
        } catch (Exception e) {
            logger.error("Error in getStructuresList() method: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public ResponseEntity<List<Reservation>> getReservationsBetweenDatesAndStructure(LocalDate startDate, LocalDate endDate, Long idStruttura) {
        try {
            logger.debug("getAvailableDay() called with structureId: {}", idStruttura);

            List<Reservation> reservations = reservationRepository.getAllBetweenDatesAndStructure(startDate, endDate, idStruttura);

            return ResponseEntity.ok(reservations);
        } catch (Exception e) {
            logger.error("Error in getAvailableDay() method: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public ResponseEntity<Structure> updateStructure(Long structureId, CreateStructureRequest structureRequest) {
        try {
            logger.debug("Enter into  StructureServiceImpl.updateStructure : Parameters : {}, {}", structureId, structureRequest);
            Structure structure = structureRepository.findById(structureId).orElseThrow(() -> new RuntimeException("Structure not found"));
            PropertiesHelper.copyNonNullProperties(structureRequest, structure);

            return ResponseEntity.ok(structureRepository.save(structure));

        } catch (Exception e) {
            logger.error("Error in StructureServiceImpl.updateStructure : {}", e);
            throw e;
        }
    }

    @Override
    public ResponseEntity<Boolean> deleteStructure(Long structureId) {
        try{
            logger.debug("Enter into  StructureServiceImpl.deleteStructure : Parameters : {}", structureId);
            Structure structure = structureRepository.findById(structureId).orElseThrow(() -> new RuntimeException("Structure not found"));
            structureRepository.delete(structure);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            logger.error("Error in StructureServiceImpl.deleteStructure : {}", e);
            throw e;
        }
    }
}
