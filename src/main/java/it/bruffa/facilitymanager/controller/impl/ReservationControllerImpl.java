package it.bruffa.facilitymanager.controller.impl;

import io.swagger.v3.oas.annotations.tags.Tag;
import it.bruffa.facilitymanager.controller.ReservationController;
import it.bruffa.facilitymanager.model.dto.ReservationFilter;
import it.bruffa.facilitymanager.model.dto.request.CreateReservationRequest;
import it.bruffa.facilitymanager.model.entity.Reservation;
import it.bruffa.facilitymanager.model.entity.Structure;
import it.bruffa.facilitymanager.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/reservation")
@Tag(name = "reservation", description = "The reservation APIs")
public class ReservationControllerImpl implements ReservationController {
    @Autowired
    private ReservationService reservationService;

    @Override
    public ResponseEntity<Page<Reservation>> filter(ReservationFilter probe, Integer page, Integer size, String sortField, String sortDirection) {
        return reservationService.filter(probe, page, size, sortField, sortDirection);
    }

    @Override
    public ResponseEntity<Reservation> getReservationById(Long reservationId) {
        return reservationService.getReservationById(reservationId);
    }

    @Override
    public ResponseEntity<Reservation> createReservation(CreateReservationRequest createReservationRequest) {
        return reservationService.createReservation(createReservationRequest);
    }

    @Override
    public ResponseEntity<Boolean> deleteReservation(Long reservationId) {
        return reservationService.deleteReservation(reservationId);
    }
}
