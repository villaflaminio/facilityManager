package it.bruffa.facilitymanager.service;

import it.bruffa.facilitymanager.model.dto.MaintenanceFilter;
import it.bruffa.facilitymanager.model.dto.request.CreateMaintenanceRequest;
import it.bruffa.facilitymanager.model.dto.request.UpdateMaintenanceRequest;
import it.bruffa.facilitymanager.model.entity.Maintenance;
import it.bruffa.facilitymanager.utilities.ResponseFile;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MaintenanceService {
    ResponseEntity<Page<Maintenance>> filter(MaintenanceFilter probe, Integer page, Integer size, String sortField, String sortDirection);

    ResponseEntity<Maintenance> getMaintenanceById(Long maintenanceId);

    ResponseEntity<Maintenance> createMaintenance(CreateMaintenanceRequest createMaintenanceRequest);

    ResponseEntity<Maintenance> updateMaintenance(Long maintenanceId, UpdateMaintenanceRequest modifyMaintenanceRequest);

    ResponseEntity<Boolean> deleteMaintenance(Long maintenanceId);

    ResponseEntity<Boolean> addPicture(Long maintenanceId, MultipartFile file);

    ResponseEntity<Boolean> deletePicture(Long pictureId);

    ResponseEntity<List<ResponseFile>> getPictures(Long documentId);

    ResponseEntity<Boolean> addDocument(Long maintenanceId, MultipartFile file);

    ResponseEntity<Boolean> deleteDocument(Long documentId) throws Exception;

    ResponseEntity<List<ResponseFile>> getDocumentS(Long maintenanceId);
}
