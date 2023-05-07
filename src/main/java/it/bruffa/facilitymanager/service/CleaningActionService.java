package it.bruffa.facilitymanager.service;

import it.bruffa.facilitymanager.model.dto.CreateCleaningActionRequest;
import it.bruffa.facilitymanager.model.dto.UpdateCleaningActionRequest;
import it.bruffa.facilitymanager.model.entity.CleaningAction;
import it.bruffa.facilitymanager.model.projection.CleaningActionInfo;
import it.bruffa.facilitymanager.utilities.ResponseFile;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

public interface CleaningActionService {
    ResponseEntity<Page<CleaningActionInfo>> filter(CleaningAction probe, Integer page, Integer size, String sortField, String sortDirection);

    ResponseEntity<CleaningActionInfo> getCleaningActionById(Long cleaningActionId);

    ResponseEntity<CleaningAction> createCleaningAction(CreateCleaningActionRequest createCleaningActionRequest);

    ResponseEntity<CleaningAction> updateCleaningAction(Long cleaningActionId, UpdateCleaningActionRequest updateCleaningActionRequest);

    ResponseEntity<Boolean> deleteCleaningAction(Long cleaningActionId);

    ResponseEntity<Boolean> addPictureToCleaningAction(Long cleaningActionId, MultipartFile file);

    ResponseEntity<Boolean> removePictureFromCleaningAction(Long pictureId);

    ResponseEntity<List<ResponseFile>> getPicturesFromCleaningAction(Long cleaningActionId);

    List<CleaningAction> assignCleaningAction(LocalDate checkOutDate);
}
