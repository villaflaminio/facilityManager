package it.bruffa.facilitymanager.repository;

import it.bruffa.facilitymanager.model.entity.AccessLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccessLogRepository extends JpaRepository<AccessLog, Long> {
}