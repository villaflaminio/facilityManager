package it.bruffa.facilitymanager.service.impl;

import it.bruffa.facilitymanager.model.dto.AuthResponseDTO;
import it.bruffa.facilitymanager.model.dto.LoginDto;
import it.bruffa.facilitymanager.model.dto.TokenRefreshResponseDto;
import it.bruffa.facilitymanager.model.dto.request.CreateFeedbackRequest;
import it.bruffa.facilitymanager.model.dto.request.SignUpRequestDto;
import it.bruffa.facilitymanager.model.entity.*;
import it.bruffa.facilitymanager.model.exception.ExpiredJwtException;
import it.bruffa.facilitymanager.model.exception.InvalidCredentialsException;
import it.bruffa.facilitymanager.model.exception.ItemNotFoundException;
import it.bruffa.facilitymanager.model.projection.UserMeInfo;
import it.bruffa.facilitymanager.repository.*;
import it.bruffa.facilitymanager.security.CustomAuthenticationManager;
import it.bruffa.facilitymanager.security.JwtUtility;
import it.bruffa.facilitymanager.service.AuthService;
import it.bruffa.facilitymanager.service.FeedbackService;
import it.bruffa.facilitymanager.service.StructureService;
import it.bruffa.facilitymanager.utilities.PropertiesHelper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

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
