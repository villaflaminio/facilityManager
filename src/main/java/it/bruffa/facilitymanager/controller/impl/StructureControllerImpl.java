package it.bruffa.facilitymanager.controller.impl;

import it.bruffa.facilitymanager.controller.StructureController;
import it.bruffa.facilitymanager.service.StructureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StructureControllerImpl implements StructureController {
    @Autowired
    private StructureService structureService;
}
