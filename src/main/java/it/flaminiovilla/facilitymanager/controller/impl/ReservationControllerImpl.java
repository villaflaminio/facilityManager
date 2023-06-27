package it.flaminiovilla.facilitymanager.controller.impl;

import io.swagger.v3.oas.annotations.tags.Tag;
import it.flaminiovilla.facilitymanager.controller.ReservationController;
import it.flaminiovilla.facilitymanager.model.dto.ReservationFilter;
import it.flaminiovilla.facilitymanager.model.dto.request.CreateReservationRequest;
import it.flaminiovilla.facilitymanager.model.entity.Reservation;
import it.flaminiovilla.facilitymanager.service.ReservationService;
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

    /***
     * Get a reservation by id
     * @param reservationId
     * @return
     */
    @Override
    public ResponseEntity<Page<Reservation>> filter(ReservationFilter probe, Integer page, Integer size, String sortField, String sortDirection) {
        return reservationService.filter(probe, page, size, sortField, sortDirection);
    }

    /***
     * Get a reservation by id
     * @param reservationId
     * @return
     */
    @Override
    public ResponseEntity<Reservation> getReservationById(Long reservationId) {
        return reservationService.getReservationById(reservationId);
    }

    /***
     * Get a reservation by id
     * @param reservationId
     * @return
     */
    @Override
    public ResponseEntity<Reservation> createReservation(CreateReservationRequest createReservationRequest) {
        return reservationService.createReservation(createReservationRequest);
    }

    /***
     * Get a reservation by id
     * @param reservationId
     * @return
     */
    @Override
    public ResponseEntity<Boolean> deleteReservation(Long reservationId) {
        return reservationService.deleteReservation(reservationId);
    }
}
