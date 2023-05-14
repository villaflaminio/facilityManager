package it.bruffa.facilitymanager.service;

import it.bruffa.facilitymanager.model.dto.AccessLogFilter;
import it.bruffa.facilitymanager.model.entity.AccessLog;
import it.bruffa.facilitymanager.model.entity.Gate;
import it.bruffa.facilitymanager.model.entity.User;
import it.bruffa.facilitymanager.model.projection.AccessLogInfo;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AccessLogService {
    ResponseEntity<Page<AccessLog>> filter(AccessLogFilter probe, Integer page, Integer size, String sortField, String sortDirection);

    boolean save(Long userId, Long gateId);
    boolean save(User user, Gate gate);
    ResponseEntity<List<AccessLogInfo>> getAccessLogByStructureId(Long structureId);
}
