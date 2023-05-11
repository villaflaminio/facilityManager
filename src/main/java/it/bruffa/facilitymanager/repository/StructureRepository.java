package it.bruffa.facilitymanager.repository;

import it.bruffa.facilitymanager.model.entity.Structure;
import it.bruffa.facilitymanager.model.projection.StructureIdInfo;
import it.bruffa.facilitymanager.model.projection.StructureInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StructureRepository extends JpaRepository<Structure, Long> {

    Optional<StructureInfo> findStructureById(Long id);

    List<StructureIdInfo> getStructureIdInfoBy();
}