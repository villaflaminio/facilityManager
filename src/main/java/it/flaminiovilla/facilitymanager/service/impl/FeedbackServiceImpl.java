package it.flaminiovilla.facilitymanager.service.impl;

import it.flaminiovilla.facilitymanager.model.dto.request.CreateFeedbackRequest;
import it.flaminiovilla.facilitymanager.model.entity.CleaningAction;
import it.flaminiovilla.facilitymanager.model.entity.Feedback;
import it.flaminiovilla.facilitymanager.model.entity.Maintenance;
import it.flaminiovilla.facilitymanager.model.entity.User;
import it.flaminiovilla.facilitymanager.model.exception.ItemNotFoundException;
import it.flaminiovilla.facilitymanager.repository.CleaningActionRepository;
import it.flaminiovilla.facilitymanager.repository.FeedbackRepository;
import it.flaminiovilla.facilitymanager.repository.MaintenanceRepository;
import it.flaminiovilla.facilitymanager.repository.UserRepository;
import it.flaminiovilla.facilitymanager.service.FeedbackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class FeedbackServiceImpl implements FeedbackService {
    @Autowired
    private FeedbackRepository feedbackRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MaintenanceRepository maintenanceRepository;
    @Autowired
    private CleaningActionRepository cleaningActionRepository;

    private static final Logger logger = LoggerFactory.getLogger(FeedbackServiceImpl.class);


    @Override
    public ResponseEntity<Feedback> createFeedback(CreateFeedbackRequest createFeedbackRequest) {
        try {
            logger.info("Creating feedback");
            User user = userRepository.findById(createFeedbackRequest.getUserId()).orElseThrow(() -> new ItemNotFoundException("User not found"));
            if (createFeedbackRequest.getMaintenanceId() != null && createFeedbackRequest.getCleaningActionId() != null) {
                logger.error("Error creating feedback: maintenanceId and cleaningActionId cannot be both not null");
                return ResponseEntity.badRequest().build();
            }


            Feedback feedback = new Feedback();
            feedback.setRatingBeforeIntervention(createFeedbackRequest.getRatingBeforeIntervention());
            feedback.setRating(createFeedbackRequest.getRating());
            feedback.setUser(user);
            feedback.setNote(createFeedbackRequest.getNote());
            feedback.setDate(LocalDateTime.now());

            if (createFeedbackRequest.getMaintenanceId() != null) {
                Maintenance maintenance = maintenanceRepository.findById(createFeedbackRequest.getMaintenanceId()).orElseThrow(() -> new ItemNotFoundException("Maintenance not found"));
                feedback.setMaintenance(maintenance);
            }

            if (createFeedbackRequest.getCleaningActionId() != null) {
                CleaningAction cleaningAction = cleaningActionRepository.findById(createFeedbackRequest.getCleaningActionId()).orElseThrow(() -> new ItemNotFoundException("CleaningAction not found"));
                feedback.setCleaningAction(cleaningAction);
            }

           Feedback saved =  feedbackRepository.save(feedback);
            if (createFeedbackRequest.getMaintenanceId() != null) {
                Maintenance maintenance = maintenanceRepository.findById(createFeedbackRequest.getMaintenanceId()).orElseThrow(() -> new ItemNotFoundException("Maintenance not found"));
                maintenance.setFeedback(saved);
                maintenanceRepository.save(maintenance);
            }

            if (createFeedbackRequest.getCleaningActionId() != null) {
                CleaningAction cleaningAction = cleaningActionRepository.findById(createFeedbackRequest.getCleaningActionId()).orElseThrow(() -> new ItemNotFoundException("CleaningAction not found"));
                cleaningAction.setFeedback(saved);
                cleaningActionRepository.save(cleaningAction);
            }

            return ResponseEntity.ok(feedback);
        } catch (Exception e) {
            logger.error("Error creating feedback", e);
            throw e;
        }
    }

    @Override
    public ResponseEntity<Boolean> deleteFeedback(Long feedbackId) {
        try {
            logger.info("Deleting feedback");
            Feedback feedback = feedbackRepository.findById(feedbackId).orElseThrow(() -> new ItemNotFoundException("Feedback not found"));
            feedbackRepository.delete(feedback);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            logger.error("Error deleting feedback", e);
            return ResponseEntity.badRequest().build();
        }
    }
}
