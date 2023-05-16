package it.bruffa.facilitymanager.controller.impl;

import io.swagger.v3.oas.annotations.tags.Tag;
import it.bruffa.facilitymanager.controller.CheckListController;
import it.bruffa.facilitymanager.model.dto.request.CreateCheckListRequest;
import it.bruffa.facilitymanager.model.entity.CheckList;
import it.bruffa.facilitymanager.model.exception.ItemNotFoundException;
import it.bruffa.facilitymanager.service.CheckListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/checkLists")
@Tag(name = "checkList", description = "The CheckList APIs")
public class CheckListControllerImpl implements CheckListController {
    @Autowired
    private CheckListService checkListService;

    /***
     * Get CheckList by id
     * @param checkListId
     * @return
     * @throws ItemNotFoundException
     */
    @Override
    public ResponseEntity<CheckList> getCheckListById(Long checkListId) throws ItemNotFoundException {
        return checkListService.getCheckListById(checkListId);
    }

    /***
     * Get CheckList by name
     * @param checkListName
     * @return
     * @throws ItemNotFoundException
     */
    @Override
    public ResponseEntity<CheckList> getCheckListByName(String checkListName) throws ItemNotFoundException {
        return checkListService. getCheckListByName(checkListName);
    }

    /***
     * Create CheckList
     * @param createCheckListRequest
     * @return
     * @throws Exception
     */
    @Override
    public ResponseEntity<CheckList> createCheckList(CreateCheckListRequest createCheckListRequest) throws Exception {
        return  checkListService.createCheckList(createCheckListRequest);
    }

    /***
     * Update CheckList
     * @param checkListId
     * @param createCheckListRequest
     * @return
     * @throws ItemNotFoundException
     * @throws Exception
     */
    @Override
    public ResponseEntity<CheckList> updateCheckList(Long checkListId, CreateCheckListRequest createCheckListRequest) throws ItemNotFoundException, Exception {
        return checkListService.updateCheckList(checkListId, createCheckListRequest);
    }

    /***
     * Delete CheckList
     * @param checkListId
     * @return
     * @throws ItemNotFoundException
     */
    @Override
    public ResponseEntity<Boolean> deleteCheckList(Long checkListId) throws ItemNotFoundException {
        return checkListService.deleteCheckList(checkListId);
    }
}
