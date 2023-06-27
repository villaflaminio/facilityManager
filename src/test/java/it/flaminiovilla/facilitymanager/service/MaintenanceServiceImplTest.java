package it.flaminiovilla.facilitymanager.service;

import it.flaminiovilla.facilitymanager.model.dto.request.CreateMaintenanceRequest;
import it.flaminiovilla.facilitymanager.model.dto.request.UpdateMaintenanceRequest;
import it.flaminiovilla.facilitymanager.service.impl.MaintenanceServiceImpl;
import it.flaminiovilla.facilitymanager.model.entity.*;
import it.flaminiovilla.facilitymanager.repository.*;
import org.instancio.Instancio;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.io.FileInputStream;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

@ExtendWith(MockitoExtension.class)
public class MaintenanceServiceImplTest {

    private static final Logger log = LoggerFactory.getLogger(StructureServiceImplTest.class);
    @Mock
    private MaintenanceRepository maintenanceRepository;
    @Mock
    private StructureRepository structureRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private CheckListRepository checkListRepository;
    @Mock
    private QuoteRepository quoteRepository;
    @Mock
    private FeedbackRepository feedbackRepository;
    @Mock
    private FileRepository fileRepository;

    @InjectMocks
    private MaintenanceServiceImpl maintenanceserviceimpl;

    @Test
    @DisplayName("get Maintenance By Id")
    public void getMaintenanceById() {
        try {
            log.info("Starting execution of getMaintenanceById");
            Maintenance maintenance = Instancio.create(Maintenance.class);

            Long maintenanceId = maintenance.getId();
            when(maintenanceRepository.findById(anyLong())).thenReturn(Optional.of(maintenance));

            ResponseEntity<Maintenance> actualValue = maintenanceserviceimpl.getMaintenanceById(maintenanceId);

            Assertions.assertEquals(maintenance, actualValue.getBody());
        } catch (Exception exception) {
            log.error("Exception in execution of execute1GetAllLogFromFirstMovF-" + exception, exception);
            exception.printStackTrace();
            Assertions.assertFalse(false);
        }
    }


    @DisplayName("create Maintenance")
    @Test
    public void createMaintenance() {
        try {
            log.info("Starting execution of createMaintenance");
            CreateMaintenanceRequest createMaintenanceRequest = Instancio.create(CreateMaintenanceRequest.class);
            Structure structure = Instancio.create(Structure.class);
            User user = Instancio.create(User.class);
            CheckList checkList = Instancio.create(CheckList.class);


            when(structureRepository.findById(anyLong())).thenReturn(Optional.of(structure));
            when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
            when(checkListRepository.findById(anyLong())).thenReturn(Optional.of(checkList));
            ResponseEntity<Maintenance> actualValue = maintenanceserviceimpl.createMaintenance(createMaintenanceRequest);

            Assertions.assertEquals(actualValue.getStatusCode(), HttpStatusCode.valueOf(200));
        } catch (Exception exception) {
            log.error("Exception in execution of execute1GetAllLogFromFirstMovF-" + exception, exception);
            exception.printStackTrace();
            Assertions.assertFalse(false);
        }
    }

    @Test
    @DisplayName("update Maintenance")
    public void updateMaintenance() {
        try {
            log.info("Starting execution of updateMaintenance");
            Maintenance maintenance = Instancio.create(Maintenance.class);
            Long maintenanceId = maintenance.getId();

            when(maintenanceRepository.findById(anyLong())).thenReturn(Optional.of(maintenance));
            UpdateMaintenanceRequest maintenanceRequest = Instancio.create(UpdateMaintenanceRequest.class);

            ResponseEntity<Maintenance> actualValue = maintenanceserviceimpl.updateMaintenance(maintenanceId, maintenanceRequest);

            Assertions.assertEquals(actualValue.getStatusCode(), HttpStatusCode.valueOf(200));
        } catch (Exception exception) {
            log.error("Exception in execution of execute1GetAllLogFromFirstMovF-" + exception, exception);
            exception.printStackTrace();
            Assertions.assertFalse(false);
        }
    }

    @Test
    @DisplayName("delete Maintenance")
    public void deleteMaintenance() {
        try {
            log.info("Starting execution of deleteMaintenance");
            ResponseEntity<Boolean> expectedValue = ResponseEntity.ok(true);
            Maintenance maintenance = Instancio.create(Maintenance.class);
            Long maintenanceId = maintenance.getId();

            when(maintenanceRepository.findById(anyLong())).thenReturn(Optional.of(maintenance));

            ResponseEntity<Boolean> actualValue = maintenanceserviceimpl.deleteMaintenance(maintenanceId);
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
            Maintenance maintenance = Instancio.create(Maintenance.class);
            when(maintenanceRepository.findById(anyLong())).thenReturn(Optional.of(maintenance));

            Long maintenanceId = maintenance.getId();

            MultipartFile file = new MockMultipartFile("file.txt", new FileInputStream(new ClassPathResource("/init/file.txt").getFile()));

            ResponseEntity<Boolean> actualValue = maintenanceserviceimpl.addPicture(maintenanceId, file);
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
            File document = Instancio.create(File.class);
            when(fileRepository.findById(anyLong())).thenReturn(Optional.of(document));

            Long pictureId = document.getId();

            ResponseEntity<Boolean> actualValue = maintenanceserviceimpl.deletePicture(pictureId);
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
    @DisplayName("add Document")
    public void addDocument() {
        try {
            log.info("Starting execution of addDocument");
            ResponseEntity<Boolean> expectedValue = ResponseEntity.ok(true);
            Maintenance maintenance = Instancio.create(Maintenance.class);
            when(maintenanceRepository.findById(anyLong())).thenReturn(Optional.of(maintenance));

            Long maintenanceId = maintenance.getId();

            MultipartFile file = new MockMultipartFile("file.txt", new FileInputStream(new ClassPathResource("/init/file.txt").getFile()));

            ResponseEntity<Boolean> actualValue = maintenanceserviceimpl.addDocument(maintenanceId, file);
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
    @DisplayName("delete Document")
    public void deleteDocument() {
        try {
            log.info("Starting execution of deleteDocument");
            ResponseEntity<Boolean> expectedValue = ResponseEntity.ok(true);

            File document = Instancio.create(File.class);
            when(fileRepository.findById(anyLong())).thenReturn(Optional.of(document));

            Long documentId = document.getId();


            ResponseEntity<Boolean> actualValue = maintenanceserviceimpl.deleteDocument(documentId);
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
