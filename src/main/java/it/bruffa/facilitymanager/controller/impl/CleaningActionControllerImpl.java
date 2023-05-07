package it.bruffa.facilitymanager.controller.impl;

import it.bruffa.facilitymanager.controller.CleaningActionController;
import it.bruffa.facilitymanager.model.dto.CreateCleaningActionRequest;
import it.bruffa.facilitymanager.model.dto.UpdateCleaningActionRequest;
import it.bruffa.facilitymanager.model.entity.CleaningAction;
import it.bruffa.facilitymanager.model.exception.ItemNotFoundException;
import it.bruffa.facilitymanager.model.projection.CleaningActionInfo;
import it.bruffa.facilitymanager.service.CleaningActionService;
import it.bruffa.facilitymanager.utilities.ResponseFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class CleaningActionControllerImpl implements CleaningActionController {
    @Autowired
    private CleaningActionService cleaningActionService;

    @Override
    public ResponseEntity<Page<CleaningActionInfo>> filter(CleaningAction probe, Integer page, Integer size, String sortField, String sortDirection) {
        return cleaningActionService.filter(probe, page, size, sortField, sortDirection);
    }

    @Override
    public ResponseEntity<CleaningActionInfo> getCleaningActionById(Long cleaningActionId) throws ItemNotFoundException {
        return  cleaningActionService.getCleaningActionById(cleaningActionId);
    }

    @Override
    public ResponseEntity<CleaningAction> createCleaningAction(CreateCleaningActionRequest createCleaningActionRequest) throws Exception {
        return  cleaningActionService.createCleaningAction(createCleaningActionRequest);
    }

    @Override
    public ResponseEntity<CleaningAction> updateCleaningAction(Long cleaningActionId, UpdateCleaningActionRequest updateCleaningActionRequest) throws ItemNotFoundException, Exception {
        return  cleaningActionService.updateCleaningAction(cleaningActionId, updateCleaningActionRequest);
    }

    @Override
    public ResponseEntity<Boolean> deleteCleaningAction(Long cleaningActionId) throws ItemNotFoundException {
        return cleaningActionService.deleteCleaningAction(cleaningActionId);
    }

    @Override
    public ResponseEntity<Boolean> addPictureToCleaningAction(Long cleaningActionId, MultipartFile file) throws ItemNotFoundException {
        return cleaningActionService.addPictureToCleaningAction(cleaningActionId, file);
    }

    @Override
    public ResponseEntity<Boolean> removePictureFromCleaningAction(Long pictureId) throws ItemNotFoundException {
        return  cleaningActionService.removePictureFromCleaningAction(pictureId);
    }

    @Override
    public ResponseEntity<List<ResponseFile>> getPicturesFromCleaningAction(Long cleaningActionId) throws ItemNotFoundException {
        return  cleaningActionService.getPicturesFromCleaningAction(cleaningActionId);
    }
}
