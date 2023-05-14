package it.bruffa.facilitymanager.service.impl;

import it.bruffa.facilitymanager.model.builder.MaintenanceBuilder;
import it.bruffa.facilitymanager.model.builder.QuoteBuilder;
import it.bruffa.facilitymanager.model.dto.request.CreateMaintenanceRequest;
import it.bruffa.facilitymanager.model.entity.*;
import it.bruffa.facilitymanager.repository.*;
import it.bruffa.facilitymanager.service.AccessLogService;
import it.bruffa.facilitymanager.service.MaintenanceService;
import it.bruffa.facilitymanager.service.StructureService;
import it.bruffa.facilitymanager.utilities.ResponseFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class MaintenanceServiceImpl implements MaintenanceService {
    @Autowired
    private MaintenanceRepository maintenanceRepository;
    @Autowired
    private StructureRepository structureRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CheckListRepository checkListRepository;
    @Autowired
    private QuoteRepository quoteRepository;

    private static final Logger logger = LoggerFactory.getLogger(MaintenanceServiceImpl.class);

    @Override
    public ResponseEntity<Page<Maintenance>> filter(Maintenance probe, Integer page, Integer size, String sortField, String sortDirection) {
        return null;
    }

    @Override
    public ResponseEntity<Maintenance> getMaintenanceById(Long maintenanceId) {
        return null;
    }

    @Override
    public ResponseEntity<Maintenance> createMaintenance(CreateMaintenanceRequest createMaintenanceRequest) {
        try {
            logger.debug("Enter into createMaintenance method with request: {}", createMaintenanceRequest);
            Structure structure = structureRepository.findById(createMaintenanceRequest.getStructureId()).orElseThrow(() -> new Exception("Structure not found"));
            User user = userRepository.findById(createMaintenanceRequest.getUserId()).orElseThrow(() -> new Exception("User not found"));
            CheckList checkList = checkListRepository.findById(createMaintenanceRequest.getCheckListId()).orElseThrow(() -> new Exception("CheckList not found"));

            Maintenance maintenance = MaintenanceBuilder.builder()
                    .structure(structure)
                    .user(user)
                    .checkList(checkList)
                    .description(createMaintenanceRequest.getDescription())
                    .date(createMaintenanceRequest.getDate())
                    .status(MaintenanceStatus.CREATED)
                    .build();
            Maintenance saved = maintenanceRepository.save(maintenance);

            Quote quote = QuoteBuilder.builder()
                    .maintenance(saved)
                    .description(createMaintenanceRequest.getDescription())
                    .structure(structure)
                    .user(user)
                    .build();
            quoteRepository.save(quote);


            maintenanceRepository.save(maintenance);
            logger.debug("Maintenance created: {}", maintenance);
            return ResponseEntity.ok(maintenance);
        } catch (Exception e) {
            logger.error("Error in createMaintenance method: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<Maintenance> updateMaintenance(Long maintenanceId, CreateMaintenanceRequest modifyMaintenanceRequest) {
        return null;
    }

    @Override
    public ResponseEntity<Maintenance> deleteMaintenance(Long maintenanceId) {
        return null;
    }

    @Override
    public ResponseEntity<Maintenance> addPicture(Long maintenanceId, MultipartFile file) {
        return null;
    }

    @Override
    public ResponseEntity<Boolean> deletePicture(Long maintenanceId) {
        return null;
    }

    @Override
    public ResponseEntity<List<ResponseFile>> getPicture(Long maintenanceId) {
        return null;
    }

    @Override
    public ResponseEntity<Maintenance> addDocument(Long maintenanceId, MultipartFile file) {
        return null;
    }

    @Override
    public ResponseEntity<Boolean> deleteDocument(Long maintenanceId) {
        return null;
    }
}
