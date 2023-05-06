package it.bruffa.facilitymanager.service.impl;

import it.bruffa.facilitymanager.model.entity.Reservation;
import it.bruffa.facilitymanager.repository.ReservationRepository;
import it.bruffa.facilitymanager.service.AccessLogService;
import it.bruffa.facilitymanager.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> getReservationsWithCheckOnDay(LocalDate checkOutDate) {

        List<Reservation> reservations =  reservationRepository.findAllByCheckOut(checkOutDate);

        return reservations;
    }

}
