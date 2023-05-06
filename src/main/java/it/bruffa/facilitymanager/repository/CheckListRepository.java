package it.bruffa.facilitymanager.repository;

import it.bruffa.facilitymanager.model.entity.CheckList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckListRepository extends JpaRepository<CheckList, Long> {
}