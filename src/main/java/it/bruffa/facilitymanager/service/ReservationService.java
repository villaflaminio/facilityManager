package it.bruffa.facilitymanager.service;

import it.bruffa.facilitymanager.model.dto.ReservationFilter;
import it.bruffa.facilitymanager.model.dto.request.CreateReservationRequest;
import it.bruffa.facilitymanager.model.entity.Reservation;
import it.bruffa.facilitymanager.model.entity.Structure;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface ReservationService {
    ResponseEntity<Page<Reservation>> filter(ReservationFilter probe, Integer page, Integer size, String sortField, String sortDirection);

    ResponseEntity<Reservation> getReservationById(Long reservationId);

    ResponseEntity<Reservation> createReservation(CreateReservationRequest createReservationRequest);

    ResponseEntity<Boolean> deleteReservation(Long reservationId);
}
