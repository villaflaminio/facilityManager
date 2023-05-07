package it.bruffa.facilitymanager.service.impl;

import it.bruffa.facilitymanager.controller.CheckListController;
import it.bruffa.facilitymanager.model.builder.CheckListBuilder;
import it.bruffa.facilitymanager.model.dto.request.CreateCheckListRequest;
import it.bruffa.facilitymanager.model.entity.CheckList;
import it.bruffa.facilitymanager.repository.CheckListRepository;
import it.bruffa.facilitymanager.service.AccessLogService;
import it.bruffa.facilitymanager.service.CheckListService;
import it.bruffa.facilitymanager.utilities.PropertiesHelper;
import org.hibernate.type.descriptor.java.CharacterJavaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CheckListServiceImpl implements CheckListService {
    private static final Logger logger = LoggerFactory.getLogger(CheckListServiceImpl.class);

    @Autowired
    private CheckListRepository checkListRepository;

    @Override
    public ResponseEntity<CheckList> getCheckListById(Long checkListId) {
        logger.info("getCheckListById() called with id: {}", checkListId);
        CheckList checkList = checkListRepository.findById(checkListId).orElseThrow(() -> new RuntimeException("CheckList not found"));
        return ResponseEntity.ok(checkList);
    }

    @Override
    public ResponseEntity<CheckList> getCheckListByName(String checkListName) {

        logger.info("getCheckListByName() called with id: {}", checkListName);
        CheckList checkList = checkListRepository.findByName(checkListName).orElseThrow(() -> new RuntimeException("CheckList not found"));
        return ResponseEntity.ok(checkList);
    }

    @Override
    public ResponseEntity<CheckList> createCheckList(CreateCheckListRequest createCheckListRequest) {
        try {
            logger.info("createCheckList() called with CheckList: {}", createCheckListRequest);

            CheckList newCheckList = CheckListBuilder.builder()
                    .name(createCheckListRequest.getName())
                    .description(createCheckListRequest.getDescription())
                    .items(createCheckListRequest.getItems())
                    .build();

            return ResponseEntity.ok(checkListRepository.save(newCheckList));

        } catch (Exception e) {
            logger.error("Error in createCheckList() method: {}", e.getMessage());
            throw e;
        } finally {
            logger.debug("Exit from createCheckList() method");
        }
    }

    @Override
    public ResponseEntity<CheckList> updateCheckList(Long checkListId, CreateCheckListRequest createCheckListRequest) {
        try {
            logger.info("updateCheckList() called with CheckList: {}", createCheckListRequest);
            CheckList CheckListToUpdate = checkListRepository.findById(checkListId).orElseThrow(() -> new RuntimeException("CheckList not found"));

            // Update the CheckList
            // Copy only not null properties from the request to the CheckList
            PropertiesHelper.copyNonNullProperties(createCheckListRequest, CheckListToUpdate);

            return ResponseEntity.ok(checkListRepository.save(CheckListToUpdate));

        } catch (Exception e) {
            logger.error("Error in updateCheckList() method: {}", e.getMessage());
            throw e;
        } finally {
            logger.debug("Exit from updateCheckList() method");
        }    }

    @Override
    public ResponseEntity<Boolean> deleteCheckList(Long checkListId) {
        try {
            logger.info("deleteCheckList() called with id: {}", checkListId);
            CheckList CheckListToDelete = checkListRepository.findById(checkListId).orElseThrow(() -> new RuntimeException("CheckList not found"));
            checkListRepository.delete(CheckListToDelete);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            logger.error("Error in deleteCheckList() method: {}", e.getMessage());
            throw e;
        } finally {
            logger.debug("Exit from deleteCheckList() method");
        }
    }
}
