package it.bruffa.facilitymanager.service;

import it.bruffa.facilitymanager.model.entity.AccessLog;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface AccessLogService {
    ResponseEntity<Page<AccessLog>> filter(AccessLog probe, Integer page, Integer size, String sortField, String sortDirection);
}
