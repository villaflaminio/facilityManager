package it.bruffa.facilitymanager.repository;

import it.bruffa.facilitymanager.model.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findAllByCheckOut(LocalDate checkOut);

}