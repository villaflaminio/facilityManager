package it.flaminiovilla.facilitymanager.repository;

import it.flaminiovilla.facilitymanager.model.entity.Gate;
import it.flaminiovilla.facilitymanager.model.projection.GateInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GateRepository extends JpaRepository<Gate, Long> {
    Optional<GateInfo> findByName(String gateName);
    Optional<GateInfo> getGateById(Long id);
}