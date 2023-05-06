package it.bruffa.facilitymanager.controller.impl;

import it.bruffa.facilitymanager.controller.CleaningActionController;
import it.bruffa.facilitymanager.service.CleaningActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CleaningActionControllerImpl implements CleaningActionController {
    @Autowired
    private CleaningActionService cleaningActionService;
}
