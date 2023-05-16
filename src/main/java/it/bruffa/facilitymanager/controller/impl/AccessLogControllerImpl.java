package it.bruffa.facilitymanager.controller.impl;

import io.swagger.v3.oas.annotations.tags.Tag;
import it.bruffa.facilitymanager.controller.AccessLogController;
import it.bruffa.facilitymanager.model.dto.AccessLogFilter;
import it.bruffa.facilitymanager.model.entity.AccessLog;
import it.bruffa.facilitymanager.model.projection.AccessLogInfo;
import it.bruffa.facilitymanager.service.AccessLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/api/accessLogs")
@Tag(name = "AccessLog", description = "The AccessLog APIs")
public class AccessLogControllerImpl implements AccessLogController {
    @Autowired
    private AccessLogService accessLogService;

    /***
     * Filter AccessLog
     * @param probe
     * @param page
     * @param size
     * @param sortField
     * @param sortDirection
     * @return
     */
    @Override
    public ResponseEntity<Page<AccessLog>> filter(AccessLogFilter probe, Integer page, Integer size, String sortField, String sortDirection) {
        return accessLogService.filter(probe, page, size, sortField, sortDirection);
    }

    /***
     * Get AccessLog by structureId
     * @param structureId
     * @return
     */
    @Override
    public ResponseEntity<List<AccessLogInfo>> getAccessLogByStructureId(Long structureId) {
        return  accessLogService.getAccessLogByStructureId(structureId);
    }
}
