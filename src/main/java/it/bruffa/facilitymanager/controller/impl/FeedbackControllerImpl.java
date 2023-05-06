package it.bruffa.facilitymanager.controller.impl;

import it.bruffa.facilitymanager.controller.AccessLogController;
import it.bruffa.facilitymanager.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeedbackControllerImpl implements AccessLogController {
    @Autowired
    private FeedbackService feedbackService;
}
