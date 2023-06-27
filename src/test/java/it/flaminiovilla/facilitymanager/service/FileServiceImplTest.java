package it.flaminiovilla.facilitymanager.service;

import it.flaminiovilla.facilitymanager.model.entity.File;
import it.flaminiovilla.facilitymanager.repository.FileRepository;
import it.flaminiovilla.facilitymanager.service.impl.FileServiceImpl;
import org.instancio.Instancio;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ClassPathResource;

import java.io.FileInputStream;
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
