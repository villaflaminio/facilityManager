package it.bruffa.facilitymanager.controller.impl;

import io.swagger.v3.oas.annotations.tags.Tag;
import it.bruffa.facilitymanager.controller.MaintenanceController;
import it.bruffa.facilitymanager.model.dto.MaintenanceFilter;
import it.bruffa.facilitymanager.model.dto.request.CreateMaintenanceRequest;
import it.bruffa.facilitymanager.model.dto.request.UpdateMaintenanceRequest;
import it.bruffa.facilitymanager.model.entity.Maintenance;
import it.bruffa.facilitymanager.model.exception.ItemNotFoundException;
import it.bruffa.facilitymanager.service.MaintenanceService;
import it.bruffa.facilitymanager.utilities.ResponseFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@RestController
@RequestMapping("/api/maintenance")
@Tag(name = "maintenance", description = "The maintenance APIs")
public class MaintenanceControllerImpl implements MaintenanceController {
    @Autowired
    private MaintenanceService maintenanceService;

    @Override
    public ResponseEntity<Page<Maintenance>> filter(MaintenanceFilter probe, Integer page, Integer size, String sortField, String sortDirection) {
        return maintenanceService.filter(probe, page, size, sortField, sortDirection);
    }

    @Override
    public ResponseEntity<Maintenance> getMaintenanceById(Long maintenanceId) throws ItemNotFoundException {
        return maintenanceService.getMaintenanceById(maintenanceId);
    }

    @Override
    public ResponseEntity<Maintenance> createMaintenance(CreateMaintenanceRequest createMaintenanceRequest) {
        return maintenanceService.createMaintenance(createMaintenanceRequest);
    }

    @Override
    public ResponseEntity<Maintenance> updateMaintenance(Long maintenanceId, UpdateMaintenanceRequest modifyMaintenanceRequest) throws ItemNotFoundException {
        return maintenanceService.updateMaintenance(maintenanceId, modifyMaintenanceRequest);
    }

    @Override
    public ResponseEntity<Boolean> deleteMaintenance(Long maintenanceId) throws ItemNotFoundException {
        return maintenanceService.deleteMaintenance(maintenanceId);
    }

    @Override
    public ResponseEntity<Boolean> addPicture(Long maintenanceId, MultipartFile file) throws ItemNotFoundException {
        return maintenanceService.addPicture(maintenanceId, file);
    }

    @Override
    public ResponseEntity<Boolean> deletePicture(Long pictureId) throws ItemNotFoundException {
        return maintenanceService.deletePicture(pictureId);
    }

    @Override
    public ResponseEntity<List<ResponseFile>> getPictures(Long maintenanceId) {
        return maintenanceService.getPictures(maintenanceId);
    }

    @Override
    public ResponseEntity<Boolean> addDocument(Long maintenanceId, MultipartFile file) throws ItemNotFoundException {
        return maintenanceService.addDocument(maintenanceId, file);
    }

    @Override
    public ResponseEntity<List<ResponseFile>> getDocuments(Long maintenanceId) throws ItemNotFoundException {
        return maintenanceService.getDocumentS(maintenanceId);
    }

    @Override
    public ResponseEntity<Boolean> deleteDocument(Long documentId) throws Exception {
        return maintenanceService.deleteDocument(documentId);
    }
}
