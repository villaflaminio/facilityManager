package it.bruffa.facilitymanager.controller.impl;

import it.bruffa.facilitymanager.controller.GateController;
import it.bruffa.facilitymanager.service.GateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GateControllerImpl implements GateController {
    @Autowired
    private GateService gateService;
}
