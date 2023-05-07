package it.bruffa.facilitymanager.service;

import it.bruffa.facilitymanager.model.entity.AccessLog;
import it.bruffa.facilitymanager.model.entity.Gate;
import it.bruffa.facilitymanager.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface AccessLogService {
    ResponseEntity<Page<AccessLog>> filter(AccessLog probe, Integer page, Integer size, String sortField, String sortDirection);

    boolean save(Long userId, Long gateId);
    boolean save(User user, Gate gate);
}
