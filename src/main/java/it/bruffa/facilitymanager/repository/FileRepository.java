package it.bruffa.facilitymanager.repository;

import it.bruffa.facilitymanager.model.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {
}