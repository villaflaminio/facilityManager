package it.bruffa.facilitymanager.service;

import it.bruffa.facilitymanager.model.dto.request.CreateStructureRequest;
import it.bruffa.facilitymanager.model.entity.Structure;
import org.springframework.http.ResponseEntity;

public interface StructureService {
    ResponseEntity<Structure> createStructure(CreateStructureRequest createStructureRequest);
}
