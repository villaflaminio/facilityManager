package it.bruffa.facilitymanager.service.impl;

import it.bruffa.facilitymanager.model.dto.AccessLogFilter;
import it.bruffa.facilitymanager.model.entity.AccessLog;
import it.bruffa.facilitymanager.model.entity.Gate;
import it.bruffa.facilitymanager.model.entity.Structure;
import it.bruffa.facilitymanager.model.entity.User;
import it.bruffa.facilitymanager.model.projection.AccessLogInfo;
import it.bruffa.facilitymanager.repository.AccessLogRepository;
import it.bruffa.facilitymanager.repository.GateRepository;
import it.bruffa.facilitymanager.repository.StructureRepository;
import it.bruffa.facilitymanager.repository.UserRepository;
import it.bruffa.facilitymanager.service.AccessLogService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccessLogServiceImpl implements AccessLogService {
    @Autowired
    private AccessLogRepository accessLogRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StructureRepository structureRepository;

    @Autowired
    private GateRepository gateRepository;

    private static final Logger logger = LoggerFactory.getLogger(AccessLogServiceImpl.class);

    /**
     * Handles the filtering of access logs
     *
     * @param probe
     * @param page
     * @param size
     * @param sortField
     * @param sortDirection
     * @return
     */
    @Override
    public ResponseEntity<Page<AccessLog>> filter(AccessLogFilter probe, Integer page, Integer size, String sortField, String sortDirection) {
        try {
            logger.info("filtering access log with probe: {}", probe);
            Pageable pageable;

            AccessLog filter = new AccessLog();

            // if the probe contains a structure id, we filter by structure
            if (probe.getUserId() != null && userRepository.existsById(probe.getUserId())) {
                User user = userRepository.findById(probe.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
                filter.setUser(user);
            }

            if (probe.getGateId() != null && gateRepository.existsById(probe.getGateId())) {
                Gate gate = gateRepository.findById(probe.getGateId()).orElseThrow(() -> new RuntimeException("Gate not found"));
                filter.setGate(gate);
            }


            if (StringUtils.isEmpty(sortField)) {
                pageable = PageRequest.of(page, size);
            } else {
                //sort by field
                Sort.Direction dir = StringUtils.isEmpty(sortDirection) ? Sort.Direction.ASC : Sort.Direction.valueOf(sortDirection.trim().toUpperCase());
                pageable = PageRequest.of(page, size, dir, sortField);
            }

            ExampleMatcher matcher = ExampleMatcher.matchingAll().withIgnoreCase().withIgnoreNullValues().withStringMatcher(ExampleMatcher.StringMatcher.STARTING);
            Example<AccessLog> example = Example.of(filter, matcher);

            return ResponseEntity.ok(accessLogRepository.findAll(example, pageable));
        } catch (Exception e) {
            logger.error("error filtering access log with probe: {}", probe, e);
            throw e;
        }

    }

    /***
     * Saves an access log
     * @param userId
     * @param gateId
     * @return
     */
    @Override
    public boolean save(Long userId, Long gateId) {
        try {
            logger.info("saving access log for user: {} and gate: {}", userId, gateId);
            User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
            Gate gate = gateRepository.findById(gateId).orElseThrow(() -> new RuntimeException("Gate not found"));

            // save the access log
            AccessLog accessLog = new AccessLog();
            accessLog.setUser(user);
            accessLog.setGate(gate);
            accessLog.setTimestamp(System.currentTimeMillis());
            accessLogRepository.save(accessLog);
            return true;
        } catch (Exception e) {
            throw e;
        }
    }

    /***
     * Saves an access log
     * @param user
     * @param gate
     * @return
     */
    @Override
    public boolean save(User user, Gate gate) {
        try {
            logger.info("saving access log for user: {} and gate: {}", user, gate);

            // save the access log
            AccessLog accessLog = new AccessLog();
            accessLog.setUser(user);
            accessLog.setGate(gate);
            accessLog.setTimestamp(System.currentTimeMillis());
            accessLogRepository.save(accessLog);
            return true;
        } catch (Exception e) {
            throw e;
        }
    }

    /***
     * Gets the access log by structure id
     * @param structureId
     * @return
     */
    @Override
    public ResponseEntity<List<AccessLogInfo>> getAccessLogByStructureId(Long structureId) {
        try {
            Structure structure = structureRepository.findById(structureId).orElseThrow(() -> new RuntimeException("Structure not found"));

            return ResponseEntity.ok(accessLogRepository.findByGate(structure.getGate()));
        } catch (Exception e) {
            throw e;
        }
    }
}
