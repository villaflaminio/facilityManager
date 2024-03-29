package it.flaminiovilla.facilitymanager.service;

import it.flaminiovilla.facilitymanager.model.dto.CheckListFilter;
import it.flaminiovilla.facilitymanager.model.dto.request.CreateCheckListRequest;
import it.flaminiovilla.facilitymanager.model.entity.CheckList;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CheckListService {
    ResponseEntity<CheckList> getCheckListById(Long checkListId);

    ResponseEntity<CheckList> getCheckListByName(String checkListName);

    ResponseEntity<CheckList> createCheckList(CreateCheckListRequest createCheckListRequest);

    ResponseEntity<CheckList> updateCheckList(Long checkListId, CreateCheckListRequest createCheckListRequest);

    ResponseEntity<Boolean> deleteCheckList(Long checkListId);

    ResponseEntity<List<CheckList>> getAllCheckLists();

    ResponseEntity<Page<CheckList>> filter(CheckListFilter probe, Integer page, Integer size, String sortField, String sortDirection);
}
