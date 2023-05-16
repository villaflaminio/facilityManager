package it.bruffa.facilitymanager.controller.impl;

import io.swagger.v3.oas.annotations.tags.Tag;
import it.bruffa.facilitymanager.controller.AccessLogController;
import it.bruffa.facilitymanager.controller.FeedbackController;
import it.bruffa.facilitymanager.model.dto.request.CreateFeedbackRequest;
import it.bruffa.facilitymanager.model.entity.Feedback;
import it.bruffa.facilitymanager.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/feedbacks")
@Tag(name = "feedbacks", description = "The feedbacks APIs")
public class FeedbackControllerImpl implements FeedbackController {
    @Autowired
    private FeedbackService feedbackService;

    /***
     * This method is used to get all feedbacks
     * @return
     */
    @Override
    public ResponseEntity<Feedback> createFeedback(CreateFeedbackRequest createFeedbackRequest) throws Exception {
        return  feedbackService.createFeedback(createFeedbackRequest);
    }

    /***
     * This method is used to get all feedbacks
     * @return
     */
    @Override
    public ResponseEntity<Boolean> deleteFeedback(Long feedbackId) throws Exception {
        return  feedbackService.deleteFeedback(feedbackId);
    }
}
