package it.bruffa.facilitymanager.service;

import it.bruffa.facilitymanager.model.dto.request.CreateCheckListRequest;
import it.bruffa.facilitymanager.model.dto.request.CreateStructureRequest;
import it.bruffa.facilitymanager.model.dto.request.UpdateQuoteRequest;
import it.bruffa.facilitymanager.model.entity.*;
import it.bruffa.facilitymanager.model.projection.AccessLogInfo;
import it.bruffa.facilitymanager.model.projection.StructureIdInfo;
import it.bruffa.facilitymanager.model.projection.StructureInfo;
import it.bruffa.facilitymanager.repository.*;
import it.bruffa.facilitymanager.service.impl.AccessLogServiceImpl;
import it.bruffa.facilitymanager.service.impl.CheckListServiceImpl;
import it.bruffa.facilitymanager.service.impl.QuoteServiceImpl;
import it.bruffa.facilitymanager.service.impl.StructureServiceImpl;
import org.instancio.Instancio;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.io.FileInputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

@ExtendWith(MockitoExtension.class)
public class QuoteServiceImplTest {

    private static final Logger log = LoggerFactory.getLogger(QuoteServiceImplTest.class);
    @Mock
    private QuoteRepository quoteRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private FileRepository fileRepository;
    @InjectMocks
    QuoteServiceImpl quoteserviceimpl;

    @Test
    @DisplayName("update Quote")
    public void updateQuote() {
        try {
            log.info("Starting execution of updateQuote");
            ResponseEntity<Quote> expectedValue = null;
            Quote quote = Instancio.create(Quote.class);
            when(quoteRepository.findById(anyLong())).thenReturn(Optional.of(quote));

            Long quoteId = quote.getId();
            UpdateQuoteRequest updateQuoteRequest = Instancio.create(UpdateQuoteRequest.class);


            ResponseEntity<Quote> actualValue = quoteserviceimpl.updateQuote(quoteId, updateQuoteRequest);
            Assertions.assertEquals(actualValue.getStatusCode(), HttpStatusCode.valueOf(200));

        } catch (Exception exception) {
            log.error("Exception in execution of execute1GetAllLogFromFirstMovF-" + exception, exception);
            exception.printStackTrace();
            Assertions.assertFalse(false);
        }
    }


    @Test
    @DisplayName("add File To Quote")
    public void addFileToQuote() {
        try {
            log.info("Starting execution of addFileToQuote");
            ResponseEntity<Boolean> expectedValue = ResponseEntity.ok(true);
            Quote quote = Instancio.create(Quote.class);
            when(quoteRepository.findById(anyLong())).thenReturn(Optional.of(quote));

            MultipartFile file = new MockMultipartFile("file.txt", new FileInputStream(new ClassPathResource("/init/file.txt").getFile()));
            Long quoteId = quote.getId();


            ResponseEntity<Boolean> actualValue = quoteserviceimpl.addFileToQuote(quoteId, file);
            log.info("Expected Value=" + expectedValue + " . Actual Value=" + actualValue);
            System.out.println("Expected Value=" + expectedValue + " . Actual Value=" + actualValue);
            Assertions.assertEquals(expectedValue, actualValue);
        } catch (Exception exception) {
            log.error("Exception in execution of execute1GetAllLogFromFirstMovF-" + exception, exception);
            exception.printStackTrace();
            Assertions.assertFalse(false);
        }
    }


}
