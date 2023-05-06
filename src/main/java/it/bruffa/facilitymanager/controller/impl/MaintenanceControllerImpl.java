package it.bruffa.facilitymanager.controller.impl;

import it.bruffa.facilitymanager.controller.MaintenanceController;
import it.bruffa.facilitymanager.service.MaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MaintenanceControllerImpl implements MaintenanceController {
    @Autowired
    private MaintenanceService maintenanceService;
}
