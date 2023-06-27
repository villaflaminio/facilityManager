package it.flaminiovilla.facilitymanager.controller.impl;

import io.swagger.v3.oas.annotations.tags.Tag;
import it.flaminiovilla.facilitymanager.controller.StructureController;
import it.flaminiovilla.facilitymanager.model.dto.StructureFilter;
import it.flaminiovilla.facilitymanager.model.dto.request.CreateStructureRequest;
import it.flaminiovilla.facilitymanager.model.entity.Reservation;
import it.flaminiovilla.facilitymanager.model.entity.Structure;
import it.flaminiovilla.facilitymanager.model.exception.ItemNotFoundException;
import it.flaminiovilla.facilitymanager.model.projection.StructureIdInfo;
import it.flaminiovilla.facilitymanager.model.projection.StructureInfo;
import it.flaminiovilla.facilitymanager.service.StructureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
@RestController
@RequestMapping("/api/structures")
@Tag(name = "structure", description = "The structures APIs")
public class StructureControllerImpl implements StructureController {
    @Autowired
    private StructureService structureService;

    /***
     * Filter structures
     * @param probe
     * @param page
     * @param size
     * @param sortField
     * @param sortDirection
     * @return
     */
    @Override
    public ResponseEntity<Page<Structure>> filter(StructureFilter probe, Integer page, Integer size, String sortField, String sortDirection) {
        return structureService.filter(probe, page, size, sortField, sortDirection);
    }

    /***
     * Get structure by id
     * @param structureId
     * @return
     * @throws ItemNotFoundException
     */
    @Override
    public ResponseEntity<StructureInfo> getStructureById(Long structureId) throws ItemNotFoundException {
        return structureService.getStructureById(structureId);
    }

    /***
     * Get structures list
     * @return
     * @throws ItemNotFoundException
     */
    @Override
    public ResponseEntity<List<StructureIdInfo>> getStructuresList() throws ItemNotFoundException {
        return structureService.getStructuresList();
    }

    /***
     * Get reservations between dates and structure
     * @param startDate
     * @param endDate
     * @param structureId
     * @return
     * @throws ItemNotFoundException
     */
    @Override
    public ResponseEntity<List<Reservation>> getReservationsBetweenDatesAndStructure(LocalDate startDate, LocalDate endDate, Long structureId) throws ItemNotFoundException {
        return structureService.getReservationsBetweenDatesAndStructure( startDate,  endDate, structureId);
    }

    /***
     * Create structure
     * @param createStructureRequest
     * @return
     * @throws Exception
     */
    @Override
    public ResponseEntity<Structure> createStructure(CreateStructureRequest createStructureRequest) throws Exception {
        return structureService.createStructure(createStructureRequest);
    }

    /***
     * Update structure
     * @param structureId
     * @param structureRequest
     * @return
     * @throws ItemNotFoundException
     * @throws Exception
     */
    @Override
    public ResponseEntity<Structure> updateStructure(Long structureId, CreateStructureRequest structureRequest) throws ItemNotFoundException, Exception {
        return structureService.updateStructure(structureId, structureRequest);
    }

    /***
     * Delete structure
     * @param structureId
     * @return
     * @throws ItemNotFoundException
     */
    @Override
    public ResponseEntity<Boolean> deleteStructure(Long structureId) throws ItemNotFoundException {
        return structureService.deleteStructure(structureId);
    }
}
