package it.flaminiovilla.facilitymanager.service;

import it.flaminiovilla.facilitymanager.model.dto.request.UpdateQuoteRequest;
import it.flaminiovilla.facilitymanager.model.entity.Quote;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface QuoteService {
    ResponseEntity<Quote> updateQuote(Long quoteId, UpdateQuoteRequest updateQuoteRequest) throws Exception;

    ResponseEntity<Boolean> addFileToQuote(Long quoteId, MultipartFile file);
}
