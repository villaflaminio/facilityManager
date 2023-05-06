package it.bruffa.facilitymanager.controller.impl;

import it.bruffa.facilitymanager.controller.QuoteController;
import it.bruffa.facilitymanager.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuoteControllerImpl implements QuoteController {
    @Autowired
    private QuoteService quoteService;
}
