package it.flaminiovilla.facilitymanager.service;
import it.flaminiovilla.facilitymanager.model.dto.request.CreateCheckListRequest;
import it.flaminiovilla.facilitymanager.model.entity.CheckList;
import it.flaminiovilla.facilitymanager.service.impl.CheckListServiceImpl;
import it.flaminiovilla.facilitymanager.repository.CheckListRepository;
import org.instancio.Instancio;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@ExtendWith(MockitoExtension.class)
public class CheckListServiceImplTest {

    private static final Logger log = LoggerFactory.getLogger(CheckListServiceImplTest.class);
    @Mock
    private CheckListRepository checkListRepository;
    @InjectMocks
    private CheckListServiceImpl checkListserviceimpl;
    @Test
    @DisplayName("get CheckList By Id")
    public void getCheckListById() {
        try {
            log.info("Starting execution of getCheckListById");
            CheckList checkList = Instancio.create(CheckList.class);

            when(checkListRepository.findById(anyLong())).thenReturn(Optional.of(checkList));
            Long checkListId = checkList.getId();

            ResponseEntity<CheckList> actualValue = checkListserviceimpl.getCheckListById(checkListId);
            Assertions.assertEquals(checkList, actualValue.getBody());
        } catch (Exception exception) {
            log.error("Exception in execution of execute1GetAllLogFromFirstMovF-" + exception, exception);
            exception.printStackTrace();
            Assertions.assertFalse(false);
        }
    }

    @Test
    @DisplayName("get Check List By Name")
    public void getCheckListByName() {
        try {
            log.info("Starting execution of getCheckListByName");
            log.info("Starting execution of getCheckListById");

            CheckList checkList = Instancio.create(CheckList.class);

            when(checkListRepository.findByName(any())).thenReturn(Optional.of(checkList));

            ResponseEntity<CheckList> actualValue = checkListserviceimpl.getCheckListByName(checkList.getName());
            Assertions.assertEquals(checkList, actualValue.getBody());
        } catch (Exception exception) {
            log.error("Exception in execution of execute1GetAllLogFromFirstMovF-" + exception, exception);
            exception.printStackTrace();
            Assertions.assertFalse(false);
        }
    }

    @DisplayName("create CheckList")
    @Test
    public void createCheckList() {
        try {
            log.info("Starting execution of createCheckList");
            CreateCheckListRequest createCheckListRequest = Instancio.create(CreateCheckListRequest.class);

            ResponseEntity<CheckList> actualValue = checkListserviceimpl.createCheckList(createCheckListRequest);

            Assertions.assertEquals(actualValue.getStatusCode(), HttpStatusCode.valueOf(200));
        } catch (Exception exception) {
            log.error("Exception in execution of execute1GetAllLogFromFirstMovF-" + exception, exception);
            exception.printStackTrace();
            Assertions.assertFalse(false);
        }
    }

    @Test
    @DisplayName("update CheckList")
    public void updateCheckList() {
        try {
            log.info("Starting execution of updateCheckList");
            CheckList checkList = Instancio.create(CheckList.class);
            Long checkListId = checkList.getId();

            when(checkListRepository.findById(anyLong())).thenReturn(Optional.of(checkList));
            CreateCheckListRequest checkListRequest =  Instancio.create(CreateCheckListRequest.class);


            ResponseEntity<CheckList> actualValue = checkListserviceimpl.updateCheckList(checkListId, checkListRequest);

            Assertions.assertEquals(actualValue.getStatusCode(), HttpStatusCode.valueOf(200));
        } catch (Exception exception) {
            log.error("Exception in execution of execute1GetAllLogFromFirstMovF-" + exception, exception);
            exception.printStackTrace();
            Assertions.assertFalse(false);
        }
    }
    @Test
    @DisplayName("delete CheckList")
    public void deleteCheckList() {
        try {
            log.info("Starting execution of deleteCheckList");
            ResponseEntity<Boolean> expectedValue = ResponseEntity.ok(true);
            CheckList checkList = Instancio.create(CheckList.class);
            Long checkListId = checkList.getId();

            when(checkListRepository.findById(anyLong())).thenReturn(Optional.of(checkList));

            ResponseEntity<Boolean> actualValue = checkListserviceimpl.deleteCheckList(checkListId);
            log.info("Expected Value=" + expectedValue + " . Actual Value=" + actualValue);
            System.out.println("Expected Value=" + expectedValue + " . Actual Value=" + actualValue);
            Assertions.assertEquals(expectedValue, actualValue);
        } catch (Exception exception) {
            log.error("Exception in execution of execute1GetAllLogFromFirstMovF-" + exception, exception);
            exception.printStackTrace();
            Assertions.assertFalse(false);
        }
    }


}
