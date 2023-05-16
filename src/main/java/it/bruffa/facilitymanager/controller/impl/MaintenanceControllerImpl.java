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

    /***
     * Filter maintenance
     *
     * @param probe
     * @param page
     * @param size
     * @param sortField
     * @param sortDirection
     * @return
     */
    @Override
    public ResponseEntity<Page<Maintenance>> filter(MaintenanceFilter probe, Integer page, Integer size, String sortField, String sortDirection) {
        return maintenanceService.filter(probe, page, size, sortField, sortDirection);
    }

    /***
     * Get maintenance by id
     *
     * @param maintenanceId
     * @return
     * @throws ItemNotFoundException
     */
    @Override
    public ResponseEntity<Maintenance> getMaintenanceById(Long maintenanceId) throws ItemNotFoundException {
        return maintenanceService.getMaintenanceById(maintenanceId);
    }

    /***
     * Create maintenance
     *
     * @param createMaintenanceRequest
     * @return
     */
    @Override
    public ResponseEntity<Maintenance> createMaintenance(CreateMaintenanceRequest createMaintenanceRequest) {
        return maintenanceService.createMaintenance(createMaintenanceRequest);
    }

    /***
     * Update maintenance
     *
     * @param maintenanceId
     * @param modifyMaintenanceRequest
     * @return
     * @throws ItemNotFoundException
     */
    @Override
    public ResponseEntity<Maintenance> updateMaintenance(Long maintenanceId, UpdateMaintenanceRequest modifyMaintenanceRequest) throws ItemNotFoundException {
        return maintenanceService.updateMaintenance(maintenanceId, modifyMaintenanceRequest);
    }

    /***
     * Delete maintenance
     *
     * @param maintenanceId
     * @return
     * @throws ItemNotFoundException
     */
    @Override
    public ResponseEntity<Boolean> deleteMaintenance(Long maintenanceId) throws ItemNotFoundException {
        return maintenanceService.deleteMaintenance(maintenanceId);
    }

    /***
     * Add picture
     *
     * @param maintenanceId
     * @param file
     * @return
     * @throws ItemNotFoundException
     */
    @Override
    public ResponseEntity<Boolean> addPicture(Long maintenanceId, MultipartFile file) throws ItemNotFoundException {
        return maintenanceService.addPicture(maintenanceId, file);
    }

    /***
     * Delete picture
     *
     * @param pictureId
     * @return
     * @throws ItemNotFoundException
     */
    @Override
    public ResponseEntity<Boolean> deletePicture(Long pictureId) throws ItemNotFoundException {
        return maintenanceService.deletePicture(pictureId);
    }

    /***
     * Get pictures
     *
     * @param maintenanceId
     * @return
     */
    @Override
    public ResponseEntity<List<ResponseFile>> getPictures(Long maintenanceId) {
        return maintenanceService.getPictures(maintenanceId);
    }

    /***
     * Add document
     *
     * @param maintenanceId
     * @param file
     * @return
     * @throws ItemNotFoundException
     */
    @Override
    public ResponseEntity<Boolean> addDocument(Long maintenanceId, MultipartFile file) throws ItemNotFoundException {
        return maintenanceService.addDocument(maintenanceId, file);
    }

    /***
     * Get documents
     *
     * @param maintenanceId
     * @return
     * @throws ItemNotFoundException
     */
    @Override
    public ResponseEntity<List<ResponseFile>> getDocuments(Long maintenanceId) throws ItemNotFoundException {
        return maintenanceService.getDocumentS(maintenanceId);
    }

    /***
     * Delete document
     *
     * @param documentId
     * @return
     * @throws Exception
     */
    @Override
    public ResponseEntity<Boolean> deleteDocument(Long documentId) throws Exception {
        return maintenanceService.deleteDocument(documentId);
    }
}
