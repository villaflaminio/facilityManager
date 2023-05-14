package it.bruffa.facilitymanager.controller.impl;

import it.bruffa.facilitymanager.controller.MaintenanceController;
import it.bruffa.facilitymanager.model.dto.request.CreateMaintenanceRequest;
import it.bruffa.facilitymanager.model.entity.Maintenance;
import it.bruffa.facilitymanager.model.exception.ItemNotFoundException;
import it.bruffa.facilitymanager.service.MaintenanceService;
import it.bruffa.facilitymanager.utilities.ResponseFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class MaintenanceControllerImpl implements MaintenanceController {
    @Autowired
    private MaintenanceService maintenanceService;

    @Override
    public ResponseEntity<Page<Maintenance>> filter(Maintenance probe, Integer page, Integer size, String sortField, String sortDirection) {
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
    public ResponseEntity<Maintenance> updateMaintenance(Long maintenanceId, CreateMaintenanceRequest modifyMaintenanceRequest) throws ItemNotFoundException {
        return  maintenanceService.updateMaintenance(maintenanceId, modifyMaintenanceRequest);
    }

    @Override
    public ResponseEntity<Maintenance> deleteMaintenance(Long maintenanceId) throws ItemNotFoundException {
        return  maintenanceService.deleteMaintenance(maintenanceId);
    }

    @Override
    public ResponseEntity<Maintenance> addPicture(Long maintenanceId, MultipartFile file) throws ItemNotFoundException {
        return  maintenanceService.addPicture(maintenanceId, file);
    }

    @Override
    public ResponseEntity<Boolean> deletePicture(Long maintenanceId) throws ItemNotFoundException {
        return  maintenanceService.deletePicture(maintenanceId);
    }

    @Override
    public ResponseEntity<List<ResponseFile>> getPicture(Long maintenanceId) throws ItemNotFoundException {
        return  maintenanceService.getPicture(maintenanceId);
    }

    @Override
    public ResponseEntity<Maintenance> addDocument(Long maintenanceId, MultipartFile file) throws ItemNotFoundException {
        return  maintenanceService.addDocument(maintenanceId, file);
    }

    @Override
    public ResponseEntity<Boolean> deleteDocument(Long maintenanceId) {
        return  maintenanceService.deleteDocument(maintenanceId);
    }
}
