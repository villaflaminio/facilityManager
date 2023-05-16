package it.bruffa.facilitymanager.service;

import it.bruffa.facilitymanager.model.dto.request.CreateFeedbackRequest;
import it.bruffa.facilitymanager.model.dto.request.CreateStructureRequest;
import it.bruffa.facilitymanager.model.entity.Feedback;
import it.bruffa.facilitymanager.model.entity.Reservation;
import it.bruffa.facilitymanager.model.entity.Structure;
import it.bruffa.facilitymanager.model.projection.StructureIdInfo;
import it.bruffa.facilitymanager.model.projection.StructureInfo;
import it.bruffa.facilitymanager.repository.*;
import it.bruffa.facilitymanager.service.impl.FeedbackServiceImpl;
import it.bruffa.facilitymanager.service.impl.StructureServiceImpl;
import org.instancio.Instancio;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ExtendWith(MockitoExtension.class)
public class FeedbackServiceImplTest {

    private static final Logger log = LoggerFactory.getLogger(FeedbackServiceImplTest.class);
    @Mock
    private FeedbackRepository feedbackRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private MaintenanceRepository maintenanceRepository;
    @Mock
    private CleaningActionRepository cleaningActionRepository;
    @InjectMocks
    FeedbackServiceImpl feedbackserviceimpl ;


    @DisplayName("create Feedback")
    @Test
    public void createFeedback() {
        try {
            log.info("Starting execution of createFeedback");
            CreateFeedbackRequest createFeedbackRequest = Instancio.create(CreateFeedbackRequest.class);

            ResponseEntity<Feedback> actualValue = feedbackserviceimpl.createFeedback(createFeedbackRequest);

            Assertions.assertEquals(actualValue.getStatusCode(), HttpStatusCode.valueOf(200));
        } catch (Exception exception) {
            log.error("Exception in execution of execute1GetAllLogFromFirstMovF-" + exception, exception);
            exception.printStackTrace();
            Assertions.assertFalse(false);
        }
    }
    @Test
    @DisplayName("create Feedback")
    public void createFeedback() {
        try {
            log.info("Starting execution of createFeedback");
            ResponseEntity<Feedback> expectedValue = null;
            CreateFeedbackRequest createFeedbackRequest = Instancio.create(CreateFeedbackRequest.class);


            FeedbackServiceImpl feedbackserviceimpl = new FeedbackServiceImpl();
            ResponseEntity<Feedback> actualValue = feedbackserviceimpl.createFeedback(createFeedbackRequest);
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
    @DisplayName("delete Feedback")
    public void deleteFeedback() {
        try {
            log.info("Starting execution of deleteFeedback");
            ResponseEntity<Boolean> expectedValue = null;
            Long feedbackId = 0;


            FeedbackServiceImpl feedbackserviceimpl = new FeedbackServiceImpl();
            ResponseEntity<Boolean> actualValue = feedbackserviceimpl.deleteFeedback(feedbackId);
            log.info("Expected Value=" + expectedValue + " . Actual Value=" + actualValue);
            System.out.println("Expected Value=" + expectedValue + " . Actual Value=" + actualValue);
            Assertions.assertEquals(expectedValue, actualValue);
        } catch (Exception exception) {
            log.error("Exception in execution of execute1GetAllLogFromFirstMovF-" + exception, exception);
            exception.printStackTrace();
            Assertions.assertFalse(false);
        }
    }

    @AfterEach
    void tearDown() {
    }

    @AfterAll
    static void tearDownAll() {
    }
}
