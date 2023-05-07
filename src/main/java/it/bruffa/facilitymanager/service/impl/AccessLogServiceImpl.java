package it.bruffa.facilitymanager.service.impl;

import it.bruffa.facilitymanager.model.entity.AccessLog;
import it.bruffa.facilitymanager.model.entity.Gate;
import it.bruffa.facilitymanager.model.entity.User;
import it.bruffa.facilitymanager.repository.AccessLogRepository;
import it.bruffa.facilitymanager.repository.GateRepository;
import it.bruffa.facilitymanager.repository.UserRepository;
import it.bruffa.facilitymanager.service.AccessLogService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AccessLogServiceImpl implements AccessLogService {
    @Autowired
    private AccessLogRepository accessLogRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GateRepository gateRepository;

    @Override
    public ResponseEntity<Page<AccessLog>> filter(AccessLog probe, Integer page, Integer size, String sortField, String sortDirection) {
        Pageable pageable;

        if (probe == null) {
            probe = new AccessLog();
        }

        if (StringUtils.isEmpty(sortField)) {
            pageable = PageRequest.of(page, size);
        } else {

            Sort.Direction dir = StringUtils.isEmpty(sortDirection) ? Sort.Direction.ASC : Sort.Direction.valueOf(sortDirection.trim().toUpperCase());
            pageable = PageRequest.of(page, size, dir, sortField);
        }

        ExampleMatcher matcher = ExampleMatcher.matchingAll().withIgnoreCase().withIgnoreNullValues().withStringMatcher(ExampleMatcher.StringMatcher.STARTING);
        Example<AccessLog> example = Example.of(probe, matcher);

        return ResponseEntity.ok(accessLogRepository.findAll(example, pageable));

    }

    @Override
    public boolean save(Long userId, Long gateId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Gate gate = gateRepository.findById(gateId).orElseThrow(() -> new RuntimeException("Gate not found"));

        AccessLog accessLog = new AccessLog();
        accessLog.setUser(user);
        accessLog.setGate(gate);
        accessLog.setTimestamp(System.currentTimeMillis());
        accessLogRepository.save(accessLog);
        return true;
    }

    @Override
    public boolean save(User user, Gate gate) {

        AccessLog accessLog = new AccessLog();
        accessLog.setUser(user);
        accessLog.setGate(gate);
        accessLog.setTimestamp(System.currentTimeMillis());
        accessLogRepository.save(accessLog);
        return true;
    }
}
