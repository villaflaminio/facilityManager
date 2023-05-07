package it.bruffa.facilitymanager.service;

import it.bruffa.facilitymanager.model.dto.request.CreateCheckListRequest;
import it.bruffa.facilitymanager.model.entity.CheckList;
import org.springframework.http.ResponseEntity;

public interface CheckListService {
    ResponseEntity<CheckList> getCheckListById(Long checkListId);

    ResponseEntity<CheckList> getCheckListByName(String checkListName);

    ResponseEntity<CheckList> createCheckList(CreateCheckListRequest createCheckListRequest);

    ResponseEntity<CheckList> updateCheckList(Long checkListId, CreateCheckListRequest createCheckListRequest);

    ResponseEntity<Boolean> deleteCheckList(Long checkListId);
}
