package it.bruffa.facilitymanager.controller.impl;

import it.bruffa.facilitymanager.controller.AccessLogController;
import it.bruffa.facilitymanager.service.AccessLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccessLogControllerImpl implements AccessLogController {
    @Autowired
    private AccessLogService accessLogService;
}
