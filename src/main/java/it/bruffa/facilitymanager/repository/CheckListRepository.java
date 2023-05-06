package it.bruffa.facilitymanager.repository;

import it.bruffa.facilitymanager.model.entity.CheckList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CheckListRepository extends JpaRepository<CheckList, Long> {

    Optional<CheckList> findByName(String name);

}