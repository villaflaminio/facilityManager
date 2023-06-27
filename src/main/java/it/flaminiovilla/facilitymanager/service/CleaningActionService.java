package it.flaminiovilla.facilitymanager.service;

import it.flaminiovilla.facilitymanager.model.dto.CleaningActionFilter;
import it.flaminiovilla.facilitymanager.model.dto.CreateCleaningActionRequest;
import it.flaminiovilla.facilitymanager.model.dto.UpdateCleaningActionRequest;
import it.flaminiovilla.facilitymanager.model.entity.CleaningAction;
import it.flaminiovilla.facilitymanager.model.projection.CleaningActionInfo;
import it.flaminiovilla.facilitymanager.utilities.ResponseFile;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

public interface CleaningActionService {
    ResponseEntity<Page<CleaningAction>> filter(CleaningActionFilter probe, Integer page, Integer size, String sortField, String sortDirection);

    ResponseEntity<CleaningActionInfo> getCleaningActionById(Long cleaningActionId);

    ResponseEntity<CleaningAction> createCleaningAction(CreateCleaningActionRequest createCleaningActionRequest);

    ResponseEntity<CleaningAction> updateCleaningAction(Long cleaningActionId, UpdateCleaningActionRequest updateCleaningActionRequest);

    ResponseEntity<Boolean> deleteCleaningAction(Long cleaningActionId);

    ResponseEntity<Boolean> addPictureToCleaningAction(Long cleaningActionId, MultipartFile file);

    ResponseEntity<Boolean> removePictureFromCleaningAction(Long pictureId);

    ResponseEntity<List<ResponseFile>> getPicturesFromCleaningAction(Long cleaningActionId);

    List<CleaningAction> assignCleaningAction(LocalDate checkOutDate);
}
