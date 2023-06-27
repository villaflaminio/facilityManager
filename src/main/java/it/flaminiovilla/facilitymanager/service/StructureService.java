package it.flaminiovilla.facilitymanager.service;

import it.flaminiovilla.facilitymanager.model.dto.StructureFilter;
import it.flaminiovilla.facilitymanager.model.dto.request.CreateStructureRequest;
import it.flaminiovilla.facilitymanager.model.entity.Reservation;
import it.flaminiovilla.facilitymanager.model.entity.Structure;
import it.flaminiovilla.facilitymanager.model.projection.StructureIdInfo;
import it.flaminiovilla.facilitymanager.model.projection.StructureInfo;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

public interface StructureService {
    ResponseEntity<Structure> createStructure(CreateStructureRequest createStructureRequest);

    ResponseEntity<Page<Structure>> filter(StructureFilter probe, Integer page, Integer size, String sortField, String sortDirection);

    ResponseEntity<StructureInfo> getStructureById(Long structureId);

    ResponseEntity<List<StructureIdInfo>> getStructuresList();

    ResponseEntity<List<Reservation>> getReservationsBetweenDatesAndStructure(LocalDate startDate, LocalDate endDate, Long idStruttura);
    ResponseEntity<Structure> updateStructure(Long structureId, CreateStructureRequest structureRequest);

    ResponseEntity<Boolean> deleteStructure(Long structureId);
}
