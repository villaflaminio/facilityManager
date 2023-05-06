package it.bruffa.facilitymanager.repository;

import it.bruffa.facilitymanager.model.entity.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaintenanceRepository extends JpaRepository<Maintenance, Long> {
}