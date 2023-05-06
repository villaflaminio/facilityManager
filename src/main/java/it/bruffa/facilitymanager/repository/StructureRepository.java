package it.bruffa.facilitymanager.repository;

import it.bruffa.facilitymanager.model.entity.Structure;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StructureRepository extends JpaRepository<Structure, Long> {
}