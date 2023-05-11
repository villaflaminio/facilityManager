package it.bruffa.facilitymanager.controller.impl;

import it.bruffa.facilitymanager.controller.StructureController;
import it.bruffa.facilitymanager.model.dto.StructureFilter;
import it.bruffa.facilitymanager.model.dto.request.CreateStructureRequest;
import it.bruffa.facilitymanager.model.entity.Reservation;
import it.bruffa.facilitymanager.model.entity.Structure;
import it.bruffa.facilitymanager.model.exception.ItemNotFoundException;
import it.bruffa.facilitymanager.model.projection.StructureIdInfo;
import it.bruffa.facilitymanager.model.projection.StructureInfo;
import it.bruffa.facilitymanager.service.StructureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class StructureControllerImpl implements StructureController {
    @Autowired
    private StructureService structureService;

    @Override
    public ResponseEntity<Page<Structure>> filter(StructureFilter probe, Integer page, Integer size, String sortField, String sortDirection) {
        return structureService.filter(probe, page, size, sortField, sortDirection);
    }

    @Override
    public ResponseEntity<StructureInfo> getStructureById(Long structureId) throws ItemNotFoundException {
        return structureService.getStructureById(structureId);
    }

    @Override
    public ResponseEntity<List<StructureIdInfo>> getStructuresList() throws ItemNotFoundException {
        return structureService.getStructuresList();
    }

    @Override
    public ResponseEntity<List<Reservation>> getReservationsBetweenDatesAndStructure(LocalDate startDate, LocalDate endDate, Long structureId) throws ItemNotFoundException {
        return structureService.getReservationsBetweenDatesAndStructure( startDate,  endDate, structureId);
    }

    @Override
    public ResponseEntity<Structure> createStructure(CreateStructureRequest createStructureRequest) throws Exception {
        return structureService.createStructure(createStructureRequest);
    }

    @Override
    public ResponseEntity<Structure> updateStructure(Long structureId, CreateStructureRequest structureRequest) throws ItemNotFoundException, Exception {
        return structureService.updateStructure(structureId, structureRequest);
    }

    @Override
    public ResponseEntity<Boolean> deleteStructure(Long structureId) throws ItemNotFoundException {
        return structureService.deleteStructure(structureId);
    }
}
