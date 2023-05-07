package it.bruffa.facilitymanager.controller.impl;

import it.bruffa.facilitymanager.controller.AccessLogController;
import it.bruffa.facilitymanager.model.entity.AccessLog;
import it.bruffa.facilitymanager.service.AccessLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccessLogControllerImpl implements AccessLogController {
    @Autowired
    private AccessLogService accessLogService;

    @Override
    public ResponseEntity<Page<AccessLog>> filter(AccessLog probe, Integer page, Integer size, String sortField, String sortDirection) {
        return accessLogService.filter(probe, page, size, sortField, sortDirection);
    }
}
