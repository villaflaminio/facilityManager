package it.bruffa.facilitymanager.controller.impl;

import it.bruffa.facilitymanager.controller.CheckListController;
import it.bruffa.facilitymanager.model.dto.request.CreateCheckListRequest;
import it.bruffa.facilitymanager.model.entity.CheckList;
import it.bruffa.facilitymanager.model.exception.ItemNotFoundException;
import it.bruffa.facilitymanager.service.CheckListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckListControllerImpl implements CheckListController {
    @Autowired
    private CheckListService checkListService;

    @Override
    public ResponseEntity<CheckList> getCheckListById(Long checkListId) throws ItemNotFoundException {
        return checkListService.getCheckListById(checkListId);
    }

    @Override
    public ResponseEntity<CheckList> getCheckListByName(String checkListName) throws ItemNotFoundException {
        return checkListService. getCheckListByName(checkListName);
    }

    @Override
    public ResponseEntity<CheckList> createCheckList(CreateCheckListRequest createCheckListRequest) throws Exception {
        return  checkListService.createCheckList(createCheckListRequest);
    }

    @Override
    public ResponseEntity<CheckList> updateCheckList(Long checkListId, CreateCheckListRequest createCheckListRequest) throws ItemNotFoundException, Exception {
        return checkListService.updateCheckList(checkListId, createCheckListRequest);
    }

    @Override
    public ResponseEntity<Boolean> deleteCheckList(Long checkListId) throws ItemNotFoundException {
        return checkListService.deleteCheckList(checkListId);
    }
}
