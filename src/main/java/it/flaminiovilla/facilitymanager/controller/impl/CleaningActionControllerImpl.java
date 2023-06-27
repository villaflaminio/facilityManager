package it.flaminiovilla.facilitymanager.controller.impl;

import io.swagger.v3.oas.annotations.tags.Tag;
import it.flaminiovilla.facilitymanager.controller.CleaningActionController;
import it.flaminiovilla.facilitymanager.model.dto.CleaningActionFilter;
import it.flaminiovilla.facilitymanager.model.dto.CreateCleaningActionRequest;
import it.flaminiovilla.facilitymanager.model.dto.UpdateCleaningActionRequest;
import it.flaminiovilla.facilitymanager.model.entity.CleaningAction;
import it.flaminiovilla.facilitymanager.model.exception.ItemNotFoundException;
import it.flaminiovilla.facilitymanager.model.projection.CleaningActionInfo;
import it.flaminiovilla.facilitymanager.service.CleaningActionService;
import it.flaminiovilla.facilitymanager.utilities.ResponseFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@RestController
@RequestMapping("/api/cleaningActions")
@Tag(name = "cleaningAction", description = "The cleaningActions APIs")
public class CleaningActionControllerImpl implements CleaningActionController {
    @Autowired
    private CleaningActionService cleaningActionService;

    /***
     * This method is used to filter cleaningActions
     * @param probe
     * @param page
     * @param size
     * @param sortField
     * @param sortDirection
     * @return
     */
    @Override
    public ResponseEntity<Page<CleaningAction>> filter(CleaningActionFilter probe, Integer page, Integer size, String sortField, String sortDirection) {
        return cleaningActionService.filter(probe, page, size, sortField, sortDirection);
    }

    /***
     * This method is used to get all cleaningActions
     * @return
     */
    @Override
    public ResponseEntity<CleaningActionInfo> getCleaningActionById(Long cleaningActionId) throws ItemNotFoundException {
        return  cleaningActionService.getCleaningActionById(cleaningActionId);
    }

    /***
     * This method is used to create cleaningActions
     * @return
     */
    @Override
    public ResponseEntity<CleaningAction> createCleaningAction(CreateCleaningActionRequest createCleaningActionRequest) throws Exception {
        return  cleaningActionService.createCleaningAction(createCleaningActionRequest);
    }

    /***
     * This method is used to update cleaningActions
     * @return
     */
    @Override
    public ResponseEntity<CleaningAction> updateCleaningAction(Long cleaningActionId, UpdateCleaningActionRequest updateCleaningActionRequest) throws ItemNotFoundException, Exception {
        return  cleaningActionService.updateCleaningAction(cleaningActionId, updateCleaningActionRequest);
    }

    /***
     * This method is used to delete cleaningActions
     * @return
     */
    @Override
    public ResponseEntity<Boolean> deleteCleaningAction(Long cleaningActionId) throws ItemNotFoundException {
        return cleaningActionService.deleteCleaningAction(cleaningActionId);
    }

    /***
     * This method is used to add pictures to cleaningActions
     * @return
     */
    @Override
    public ResponseEntity<Boolean> addPictureToCleaningAction(Long cleaningActionId, MultipartFile file) throws ItemNotFoundException {
        return cleaningActionService.addPictureToCleaningAction(cleaningActionId, file);
    }

    /***
     * This method is used to remove pictures from cleaningActions
     * @return
     */
    @Override
    public ResponseEntity<Boolean> removePictureFromCleaningAction(Long pictureId) throws ItemNotFoundException {
        return  cleaningActionService.removePictureFromCleaningAction(pictureId);
    }

    /***
     * This method is used to get pictures from cleaningActions
     * @return
     */
    @Override
    public ResponseEntity<List<ResponseFile>> getPicturesFromCleaningAction(Long cleaningActionId) throws ItemNotFoundException {
        return  cleaningActionService.getPicturesFromCleaningAction(cleaningActionId);
    }
}
