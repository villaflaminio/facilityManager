package it.flaminiovilla.facilitymanager.service;

import it.flaminiovilla.facilitymanager.model.dto.CreateCleaningActionRequest;
import it.flaminiovilla.facilitymanager.model.dto.UpdateCleaningActionRequest;
import it.flaminiovilla.facilitymanager.model.projection.CleaningActionInfo;
import it.flaminiovilla.facilitymanager.service.impl.CleaningActionServiceImpl;
import it.flaminiovilla.facilitymanager.service.impl.ReservationServiceImpl;
import it.flaminiovilla.facilitymanager.model.entity.*;
import it.flaminiovilla.facilitymanager.repository.*;
import org.instancio.Instancio;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.io.FileInputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

@ExtendWith(MockitoExtension.class)
public class CleaningActionServiceImplTest {

    private static final Logger log = LoggerFactory.getLogger(CleaningActionServiceImplTest.class);
    @InjectMocks
    private CleaningActionServiceImpl cleaningActionserviceimpl;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private ReservationServiceImpl reservationServiceImpl;

    @Mock
    private CleaningActionRepository cleaningActionRepository;
    @Mock
    private StructureRepository structureRepository;

    @Mock
    private CheckListRepository checkListRepository;

    @Mock
    private FileRepository fileRepository;

    ProjectionFactory factory = new SpelAwareProxyProjectionFactory();


    @Test
    @DisplayName("get CleaningAction By Id")
    public void getCleaningActionById() {
        try {
            log.info("Starting execution of getCleaningActionById");
            CleaningAction cleaningAction = Instancio.create(CleaningAction.class);
            CleaningActionInfo projection = factory.createProjection(CleaningActionInfo.class, cleaningAction);

            when(cleaningActionRepository.findCleaningActionInfoById(anyLong())).thenReturn(Optional.of(projection));
            Long cleaningActionId = cleaningAction.getId();

            ResponseEntity<CleaningActionInfo> actualValue = cleaningActionserviceimpl.getCleaningActionById(cleaningActionId);
            Assertions.assertEquals(projection, actualValue.getBody());
        } catch (Exception exception) {
            log.error("Exception in execution of execute1GetAllLogFromFirstMovF-" + exception, exception);
            exception.printStackTrace();
            Assertions.assertFalse(false);
        }
    }

    @DisplayName("create CleaningAction")
    @Test
    public void createCleaningAction() {
        try {
            log.info("Starting execution of createCleaningAction");
            CreateCleaningActionRequest createCleaningActionRequest = Instancio.create(CreateCleaningActionRequest.class);

            ResponseEntity<CleaningAction> actualValue = cleaningActionserviceimpl.createCleaningAction(createCleaningActionRequest);

            Assertions.assertEquals(actualValue.getStatusCode(), HttpStatusCode.valueOf(200));
        } catch (Exception exception) {
            log.error("Exception in execution of execute1GetAllLogFromFirstMovF-" + exception, exception);
            exception.printStackTrace();
            Assertions.assertFalse(false);
        }
    }

    @Test
    @DisplayName("update CleaningAction")
    public void updateCleaningAction() {
        try {
            log.info("Starting execution of updateCleaningAction");
            CleaningAction cleaningAction = Instancio.create(CleaningAction.class);
            Long cleaningActionId = cleaningAction.getId();

            when(cleaningActionRepository.findById(anyLong())).thenReturn(Optional.of(cleaningAction));
            UpdateCleaningActionRequest cleaningActionRequest = Instancio.create(UpdateCleaningActionRequest.class);


            ResponseEntity<CleaningAction> actualValue = cleaningActionserviceimpl.updateCleaningAction(cleaningActionId, cleaningActionRequest);

            Assertions.assertEquals(actualValue.getStatusCode(), HttpStatusCode.valueOf(200));
        } catch (Exception exception) {
            log.error("Exception in execution of execute1GetAllLogFromFirstMovF-" + exception, exception);
            exception.printStackTrace();
            Assertions.assertFalse(false);
        }
    }

    @Test
    @DisplayName("delete CleaningAction")
    public void deleteCleaningAction() {
        try {
            log.info("Starting execution of deleteCleaningAction");
            ResponseEntity<Boolean> expectedValue = ResponseEntity.ok(true);
            CleaningAction cleaningAction = Instancio.create(CleaningAction.class);
            Long cleaningActionId = cleaningAction.getId();

            when(cleaningActionRepository.findById(anyLong())).thenReturn(Optional.of(cleaningAction));

            ResponseEntity<Boolean> actualValue = cleaningActionserviceimpl.deleteCleaningAction(cleaningActionId);
            log.info("Expected Value=" + expectedValue + " . Actual Value=" + actualValue);
            System.out.println("Expected Value=" + expectedValue + " . Actual Value=" + actualValue);
            Assertions.assertEquals(expectedValue, actualValue);
        } catch (Exception exception) {
            log.error("Exception in execution of execute1GetAllLogFromFirstMovF-" + exception, exception);
            exception.printStackTrace();
            Assertions.assertFalse(false);
        }
    }

    @Test
    @DisplayName("add Picture")
    public void addPicture() {
        try {
            log.info("Starting execution of addPicture");
            ResponseEntity<Boolean> expectedValue = ResponseEntity.ok(true);
            CleaningAction cleaningAction = Instancio.create(CleaningAction.class);
            Long cleaningActionId = cleaningAction.getId();

            when(cleaningActionRepository.findById(anyLong())).thenReturn(Optional.of(cleaningAction));

            MultipartFile file = new MockMultipartFile("file.txt", new FileInputStream(new ClassPathResource("/init/file.txt").getFile()));

            ResponseEntity<Boolean> actualValue = cleaningActionserviceimpl.addPictureToCleaningAction(cleaningActionId, file);
            log.info("Expected Value=" + expectedValue + " . Actual Value=" + actualValue);
            System.out.println("Expected Value=" + expectedValue + " . Actual Value=" + actualValue);
            Assertions.assertEquals(expectedValue, actualValue);
        } catch (Exception exception) {
            log.error("Exception in execution of execute1GetAllLogFromFirstMovF-" + exception, exception);
            exception.printStackTrace();
            Assertions.assertFalse(false);
        }
    }

    @Test
    @DisplayName("delete Picture")
    public void deletePicture() {
        try {
            log.info("Starting execution of deletePicture");
            ResponseEntity<Boolean> expectedValue = ResponseEntity.ok(true);
            CleaningAction cleaningAction = Instancio.create(CleaningAction.class);
            when(cleaningActionRepository.findById(anyLong())).thenReturn(Optional.of(cleaningAction));

            Long cleaningActionId = cleaningAction.getId();

            ResponseEntity<Boolean> actualValue = cleaningActionserviceimpl.deleteCleaningAction(cleaningActionId);
            log.info("Expected Value=" + expectedValue + " . Actual Value=" + actualValue);
            System.out.println("Expected Value=" + expectedValue + " . Actual Value=" + actualValue);
            Assertions.assertEquals(expectedValue, actualValue);
        } catch (Exception exception) {
            log.error("Exception in execution of execute1GetAllLogFromFirstMovF-" + exception, exception);
            exception.printStackTrace();
            Assertions.assertFalse(false);
        }
    }

    @Test
    @DisplayName("assign Cleaning Action")
    public void assignCleaningAction() {
        try {
            log.info("Starting execution of assignCleaningAction");
            List<CleaningAction> expectedValue = Instancio.ofList(CleaningAction.class).size(10).create();
            LocalDate checkOutDate = Instancio.create(LocalDate.class);


            Role cleanerRole = Instancio.create(Role.class);
            CheckList checkList = Instancio.create(CheckList.class);
            List<Reservation> reservationsWithCleaningNeeds = Instancio.ofList(Reservation.class).size(10).create();
            List<User> cleaners = Instancio.ofList(User.class).size(10).create();


            when(reservationServiceImpl.getReservationsWithCheckOnDay(any(LocalDate.class))).thenReturn(reservationsWithCleaningNeeds);
            when(userRepository.findByRoles(any(Role.class))).thenReturn(cleaners);
            when(checkListRepository.findByName(anyString())).thenReturn(Optional.of(checkList));
            when(roleRepository.findByName(anyString())).thenReturn(Optional.of(cleanerRole));

            List<CleaningAction> actualValue = cleaningActionserviceimpl.assignCleaningAction(checkOutDate);

        } catch (Exception exception) {
            log.error("Exception in execution of execute1GetAllLogFromFirstMovF-" + exception, exception);
            exception.printStackTrace();
            Assertions.assertFalse(false);
        }
    }


}
