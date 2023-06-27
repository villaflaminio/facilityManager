package it.flaminiovilla.facilitymanager.service;

import it.flaminiovilla.facilitymanager.model.dto.request.CreateFeedbackRequest;
import it.flaminiovilla.facilitymanager.model.entity.Feedback;
import org.springframework.http.ResponseEntity;

public interface FeedbackService {
    ResponseEntity<Feedback> createFeedback(CreateFeedbackRequest createFeedbackRequest);

    ResponseEntity<Boolean> deleteFeedback(Long feedbackId);
}
