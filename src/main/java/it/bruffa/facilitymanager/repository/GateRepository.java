package it.bruffa.facilitymanager.repository;

import it.bruffa.facilitymanager.model.entity.Gate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GateRepository extends JpaRepository<Gate, Long> {
}