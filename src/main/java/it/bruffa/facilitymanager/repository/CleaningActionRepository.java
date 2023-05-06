package it.bruffa.facilitymanager.repository;

import it.bruffa.facilitymanager.model.entity.CleaningAction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CleaningActionRepository extends JpaRepository<CleaningAction, Long> {
}