package it.bruffa.facilitymanager.repository;

import it.bruffa.facilitymanager.model.entity.Gate;
import it.bruffa.facilitymanager.model.projection.GateInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GateRepository extends JpaRepository<Gate, Long> {
    Optional<GateInfo> findByName(String gateName);
    Optional<GateInfo> getGateById(Long id);
}