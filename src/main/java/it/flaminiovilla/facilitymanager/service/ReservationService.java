package it.flaminiovilla.facilitymanager.service;

import it.flaminiovilla.facilitymanager.model.dto.ReservationFilter;
import it.flaminiovilla.facilitymanager.model.dto.request.CreateReservationRequest;
import it.flaminiovilla.facilitymanager.model.entity.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface ReservationService {
    ResponseEntity<Page<Reservation>> filter(ReservationFilter probe, Integer page, Integer size, String sortField, String sortDirection);

    ResponseEntity<Reservation> getReservationById(Long reservationId);

    ResponseEntity<Reservation> createReservation(CreateReservationRequest createReservationRequest);

    ResponseEntity<Boolean> deleteReservation(Long reservationId);
}
