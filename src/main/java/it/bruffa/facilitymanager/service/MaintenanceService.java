package it.bruffa.facilitymanager.service;

import it.bruffa.facilitymanager.model.dto.request.CreateMaintenanceRequest;
import it.bruffa.facilitymanager.model.entity.Maintenance;
import it.bruffa.facilitymanager.utilities.ResponseFile;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MaintenanceService {
    ResponseEntity<Page<Maintenance>> filter(Maintenance probe, Integer page, Integer size, String sortField, String sortDirection);

    ResponseEntity<Maintenance> getMaintenanceById(Long maintenanceId);

    ResponseEntity<Maintenance> createMaintenance(CreateMaintenanceRequest createMaintenanceRequest);

    ResponseEntity<Maintenance> updateMaintenance(Long maintenanceId, CreateMaintenanceRequest modifyMaintenanceRequest);

    ResponseEntity<Maintenance> deleteMaintenance(Long maintenanceId);

    ResponseEntity<Maintenance> addPicture(Long maintenanceId, MultipartFile file);

    ResponseEntity<Boolean> deletePicture(Long maintenanceId);

    ResponseEntity<List<ResponseFile>> getPicture(Long maintenanceId);

    ResponseEntity<Maintenance> addDocument(Long maintenanceId, MultipartFile file);

    ResponseEntity<Boolean> deleteDocument(Long maintenanceId);
}
