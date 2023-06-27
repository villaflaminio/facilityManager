package it.flaminiovilla.facilitymanager.repository;

import it.flaminiovilla.facilitymanager.model.entity.CleaningAction;
import it.flaminiovilla.facilitymanager.model.projection.CleaningActionInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CleaningActionRepository extends JpaRepository<CleaningAction, Long> {
//    Page<CleaningAction> findAll(Example<CleaningAction> example, Pageable pageable);
    Optional<CleaningActionInfo> findCleaningActionInfoById(Long id);

}