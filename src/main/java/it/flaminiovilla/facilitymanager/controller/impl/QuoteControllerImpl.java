package it.flaminiovilla.facilitymanager.controller.impl;

import io.swagger.v3.oas.annotations.tags.Tag;
import it.flaminiovilla.facilitymanager.controller.QuoteController;
import it.flaminiovilla.facilitymanager.model.dto.request.UpdateQuoteRequest;
import it.flaminiovilla.facilitymanager.model.entity.Quote;
import it.flaminiovilla.facilitymanager.model.exception.ItemNotFoundException;
import it.flaminiovilla.facilitymanager.service.QuoteService;
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

    /***
     * Get a quote by id
     * @param quoteId
     * @param updateQuoteRequest
     * @return
     * @throws ItemNotFoundException
     * @throws Exception
     */
    @Override
    public ResponseEntity<Quote> updateQuote(Long quoteId, UpdateQuoteRequest updateQuoteRequest) throws ItemNotFoundException, Exception {
        return  quoteService.updateQuote(quoteId, updateQuoteRequest);
    }

    /***
     * Add a file to a quote
     * @param quoteId
     * @param file
     * @return
     * @throws ItemNotFoundException
     */
    @Override
    public ResponseEntity<Boolean> addFileToQuote(Long quoteId, MultipartFile file) throws ItemNotFoundException {
        return quoteService.addFileToQuote(quoteId, file);
    }
}
