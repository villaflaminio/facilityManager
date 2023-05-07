package it.bruffa.facilitymanager.repository;

import it.bruffa.facilitymanager.model.entity.CleaningAction;
import it.bruffa.facilitymanager.model.projection.CleaningActionInfo;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CleaningActionRepository extends JpaRepository<CleaningAction, Long> {
    Page<CleaningActionInfo> findAllProjected(Example<CleaningAction> example, Pageable pageable);
    Optional<CleaningActionInfo> findProjectedById(Long cleaningActionId);

}