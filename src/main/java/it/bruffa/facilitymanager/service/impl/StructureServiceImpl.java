package it.bruffa.facilitymanager.service.impl;

import it.bruffa.facilitymanager.model.builder.CleaningActionBuilder;
import it.bruffa.facilitymanager.model.builder.StructureBuilder;
import it.bruffa.facilitymanager.model.dto.StructureFilter;
import it.bruffa.facilitymanager.model.dto.request.CreateStructureRequest;
import it.bruffa.facilitymanager.model.entity.CheckList;
import it.bruffa.facilitymanager.model.entity.CleaningAction;
import it.bruffa.facilitymanager.model.entity.Structure;
import it.bruffa.facilitymanager.model.entity.User;
import it.bruffa.facilitymanager.model.projection.StructureIdInfo;
import it.bruffa.facilitymanager.model.projection.StructureInfo;
import it.bruffa.facilitymanager.repository.StructureRepository;
import it.bruffa.facilitymanager.service.CleaningActionService;
import it.bruffa.facilitymanager.service.StructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StructureServiceImpl implements StructureService {
    private static final Logger logger = LoggerFactory.getLogger(StructureService.class);

    @Autowired
    private StructureRepository structureRepository;

    @Override
    public ResponseEntity<Structure> createStructure(CreateStructureRequest createStructureRequest) {

        try {
            logger.info("createStructure() called with createStructure: {}", createStructureRequest);

            Structure structure = StructureBuilder.builder()
                    .name(createStructureRequest.getName())
                    .address(createStructureRequest.getAddress())
                    .city(createStructureRequest.getCity())
                    .cap(createStructureRequest.getCap())
                    .province(createStructureRequest.getProvince())
                    .country(createStructureRequest.getCountry())
                    .latitude(createStructureRequest.getLatitude())
                    .longitude(createStructureRequest.getLongitude())
                    .description(createStructureRequest.getDescription())
                    .isActive(createStructureRequest.getIsActive())
                    .cleaningDuration(createStructureRequest.getCleaningDuration())
                    .build();

            logger.info("Structure created: {}", structure);

            return ResponseEntity.ok(structureRepository.save(structure));

        } catch (Exception e) {
            logger.error("Error in createStructure() method: {}", e.getMessage());
            throw e;
        } finally {
            logger.debug("Exit from createStructure() method");
        }
    }

    @Override
    public ResponseEntity<Page<Structure>> filter(StructureFilter probe, Integer page, Integer size, String sortField, String sortDirection) {
        return null;
    }

    @Override
    public ResponseEntity<StructureInfo> getStructureById(Long structureId) {
        return null;
    }

    @Override
    public ResponseEntity<List<StructureIdInfo>> getStructuresList() {
        return null;
    }

    @Override
    public ResponseEntity<List<String>> getAvailableDay(Long structureId) {
        return null;
    }

    @Override
    public ResponseEntity<Structure> updateStructure(Long structureId, CreateStructureRequest structureRequest) {
        return null;
    }

    @Override
    public ResponseEntity<Boolean> deleteStructure(Long structureId) {
        return null;
    }
}
