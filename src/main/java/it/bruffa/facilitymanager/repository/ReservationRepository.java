package it.bruffa.facilitymanager.repository;

import it.bruffa.facilitymanager.model.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}