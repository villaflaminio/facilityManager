package it.bruffa.facilitymanager.controller.impl;

import it.bruffa.facilitymanager.controller.StructureController;
import it.bruffa.facilitymanager.model.dto.request.CreateStructureRequest;
import it.bruffa.facilitymanager.model.entity.Structure;
import it.bruffa.facilitymanager.service.StructureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StructureControllerImpl implements StructureController {
    @Autowired
    private StructureService structureService;

    @Override
    public ResponseEntity<Structure> createStructure(CreateStructureRequest createStructureRequest) throws Exception {
        return structureService.createStructure(createStructureRequest);
    }
}
