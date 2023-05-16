package it.bruffa.facilitymanager.service;

import it.bruffa.facilitymanager.model.dto.request.CreateCheckListRequest;
import it.bruffa.facilitymanager.model.dto.request.CreateStructureRequest;
import it.bruffa.facilitymanager.model.dto.request.UpdateQuoteRequest;
import it.bruffa.facilitymanager.model.entity.*;
import it.bruffa.facilitymanager.model.projection.AccessLogInfo;
import it.bruffa.facilitymanager.model.projection.StructureIdInfo;
import it.bruffa.facilitymanager.model.projection.StructureInfo;
import it.bruffa.facilitymanager.repository.*;
import it.bruffa.facilitymanager.service.impl.*;
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
public class FileServiceImplTest {

    private static final Logger log = LoggerFactory.getLogger(QuoteServiceImplTest.class);

    @InjectMocks
    FileServiceImpl fileserviceimpl;
    @Mock
    private FileRepository fileRepository;

    @Test
    @DisplayName("store")
    public void store() {
        try {
            log.info("Starting execution of store");
            MultipartFile file = new MockMultipartFile("file.txt", new FileInputStream(new ClassPathResource("/init/file.txt").getFile()));


            File actualValue = fileserviceimpl.store(file);

        } catch (Exception exception) {
            log.error("Exception in execution of execute1GetAllLogFromFirstMovF-" + exception, exception);
            exception.printStackTrace();
            Assertions.assertFalse(false);
        }
    }


    @Test
    @DisplayName("get File")
    public void getFile() {
        try {
            log.info("Starting execution of getFile");
            File expectedValue = Instancio.create(File.class);
            Long id = expectedValue.getId();
            when(fileRepository.findById(anyLong())).thenReturn(Optional.of(expectedValue));

            File actualValue = fileserviceimpl.getFile(id);
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
