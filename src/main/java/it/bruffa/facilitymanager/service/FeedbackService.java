package it.bruffa.facilitymanager.service;

import it.bruffa.facilitymanager.model.dto.request.CreateFeedbackRequest;
import it.bruffa.facilitymanager.model.entity.Feedback;
import org.springframework.http.ResponseEntity;

public interface FeedbackService {
    ResponseEntity<Feedback> createFeedback(CreateFeedbackRequest createFeedbackRequest);

    ResponseEntity<Boolean> deleteFeedback(Long feedbackId);
}
