package it.bruffa.facilitymanager.controller.impl;

import io.swagger.v3.oas.annotations.tags.Tag;
import it.bruffa.facilitymanager.controller.QuoteController;
import it.bruffa.facilitymanager.model.dto.request.UpdateQuoteRequest;
import it.bruffa.facilitymanager.model.entity.Quote;
import it.bruffa.facilitymanager.model.exception.ItemNotFoundException;
import it.bruffa.facilitymanager.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
@RestController
@RequestMapping("/api/quotes")
@Tag(name = "quote", description = "The quotes APIs")
public class QuoteControllerImpl implements QuoteController {
    @Autowired
    private QuoteService quoteService;

    @Override
    public ResponseEntity<Quote> updateQuote(Long quoteId, UpdateQuoteRequest updateQuoteRequest) throws ItemNotFoundException, Exception {
        return  quoteService.updateQuote(quoteId, updateQuoteRequest);
    }

    @Override
    public ResponseEntity<Boolean> addFileToQuote(Long quoteId, MultipartFile file) throws ItemNotFoundException {
        return quoteService.addFileToQuote(quoteId, file);
    }
}
