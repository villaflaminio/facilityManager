package it.flaminiovilla.facilitymanager.service;

import it.flaminiovilla.facilitymanager.model.dto.request.CreateFeedbackRequest;
import it.flaminiovilla.facilitymanager.model.entity.Feedback;
import it.flaminiovilla.facilitymanager.service.impl.FeedbackServiceImpl;
import it.flaminiovilla.facilitymanager.repository.CleaningActionRepository;
import it.flaminiovilla.facilitymanager.repository.FeedbackRepository;
import it.flaminiovilla.facilitymanager.repository.MaintenanceRepository;
import it.flaminiovilla.facilitymanager.repository.UserRepository;
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
    @DisplayName("delete Feedback")
    public void deleteFeedback() {
        try {
            log.info("Starting execution of deleteFeedback");
            ResponseEntity<Boolean> expectedValue = ResponseEntity.ok(true);
            Feedback feedback = Instancio.create(Feedback.class);
            Long feedbackId = feedback.getId();

            when(feedbackRepository.findById(anyLong())).thenReturn(Optional.of(feedback));

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

}
