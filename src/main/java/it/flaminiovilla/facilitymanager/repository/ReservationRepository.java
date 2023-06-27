package it.flaminiovilla.facilitymanager.repository;

import it.flaminiovilla.facilitymanager.model.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findAllByCheckOut(LocalDate checkOut);

    @Query(value = "from Reservation r where  r.arrival BETWEEN :startDate AND :endDate AND r.departure BETWEEN :startDate AND :endDate AND r.structure.id = :idStruttura")
    List<Reservation> getAllBetweenDatesAndStructure(LocalDate startDate,LocalDate endDate, long idStruttura);

}