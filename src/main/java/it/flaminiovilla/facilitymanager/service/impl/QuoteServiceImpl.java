package it.flaminiovilla.facilitymanager.service.impl;

import it.flaminiovilla.facilitymanager.model.builder.FileBuilder;
import it.flaminiovilla.facilitymanager.model.dto.request.UpdateQuoteRequest;
import it.flaminiovilla.facilitymanager.model.entity.File;
import it.flaminiovilla.facilitymanager.model.entity.Quote;
import it.flaminiovilla.facilitymanager.repository.FileRepository;
import it.flaminiovilla.facilitymanager.repository.QuoteRepository;
import it.flaminiovilla.facilitymanager.repository.UserRepository;
import it.flaminiovilla.facilitymanager.service.QuoteService;
import it.flaminiovilla.facilitymanager.utilities.PropertiesHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class QuoteServiceImpl implements QuoteService {

    @Autowired
    private QuoteRepository quoteRepository;
    @Autowired
    private UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(QuoteServiceImpl.class);
    @Autowired
    private FileRepository fileRepository;

    @Override
    public ResponseEntity<Quote> updateQuote(Long quoteId, UpdateQuoteRequest updateQuoteRequest) throws Exception {
        try {
            logger.info("Updating quote with id: " + quoteId);
            Quote quote = quoteRepository.findById(quoteId).orElseThrow(() -> new Exception("Quote not found"));

            PropertiesHelper.copyNonNullProperties(updateQuoteRequest, quote);
            if (updateQuoteRequest.getUserId() != null && userRepository.existsById(updateQuoteRequest.getUserId()))
                quote.setUser(userRepository.findById(updateQuoteRequest.getUserId()).get());

            return ResponseEntity.ok(quoteRepository.save(quote));

        } catch (Exception e) {
            logger.error("Error updating quote with id: " + quoteId);
            throw e;
        }
    }

    @Override
    public ResponseEntity<Boolean> addFileToQuote(Long quoteId, MultipartFile file) {
        try {
            logger.debug("Enter into addFileToQuote method with id: {} and file: {}", quoteId, file);
            Quote quote = quoteRepository.findById(quoteId).orElseThrow(() -> new RuntimeException("Maintenance not found"));
            String message = "";
            try {
                String fileName = StringUtils.cleanPath(file.getOriginalFilename());

                File savedFile = FileBuilder.builder()
                        .name(fileName)
                        .type(file.getContentType())
                        .imageData(file.getBytes())
                        .quote(quote)
                        .build();
                fileRepository.save(savedFile);

                quote.setFile(savedFile);
                quoteRepository.save(quote);

                return ResponseEntity.status(HttpStatus.OK).body(true);
            } catch (Exception e) {
                logger.error("Could not upload the file: " + file.getOriginalFilename() + "!");
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
            }
        } catch (Exception e) {
            logger.error("Error in addPicture method: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}
