package it.flaminiovilla.facilitymanager.repository;

import it.flaminiovilla.facilitymanager.model.entity.AccessLog;
import it.flaminiovilla.facilitymanager.model.entity.Gate;
import it.flaminiovilla.facilitymanager.model.projection.AccessLogInfo;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccessLogRepository extends JpaRepository<AccessLog, Long> {

    List<AccessLogInfo> findByGate(Gate gateId);

    Page<AccessLogInfo> findAllBy(Example<AccessLog> example, Pageable pageable);
}