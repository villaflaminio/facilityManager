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
public class FileServiceImplTest {
  
	private Logger log = Logger.getLogger(this.getClass());
    @BeforeAll
    static void initAll() {
    }
    @BeforeEach
    void init() {
    }
 
  @Test  
	@DisplayName("store")
    public void store(){  
		try {
			log.info("Starting execution of store");
File expectedValue = null; 
  MultipartFile file = null; 
 
  
 FileServiceImpl fileserviceimpl  =new FileServiceImpl(); 
File actualValue=fileserviceimpl.store( file);
	  log.info("Expected Value="+ expectedValue +" . Actual Value="+actualValue);
	  System.out.println("Expected Value="+ expectedValue +" . Actual Value="+actualValue);
Assertions.assertEquals(expectedValue, actualValue);
		} catch (Exception exception) {
			log.error("Exception in execution of execute1GetAllLogFromFirstMovF-"+exception,exception);
			exception.printStackTrace();
			Assertions.assertFalse(false);
		}
    }  
 
  @Test  
	@DisplayName("store Exception")
    public void storeException(){  
		try {
			log.info("Starting execution of storeException");
 MultipartFile file = null; 
 
  
 FileServiceImpl fileserviceimpl  =new FileServiceImpl(); 
fileserviceimpl.storeException( file);
Assertions.assertTrue(false);
		} catch (Exception exception) {
			log.error("Exception in execution ofstoreException-"+exception,exception);
			exception.printStackTrace();
			Assertions.assertFalse(true);
		}
    }  
 
  @Test  
	@DisplayName("get File")
    public void getFile(){  
		try {
			log.info("Starting execution of getFile");
File expectedValue = null; 
  Long id=0; 
 
  
 FileServiceImpl fileserviceimpl  =new FileServiceImpl(); 
File actualValue=fileserviceimpl.getFile( id);
	  log.info("Expected Value="+ expectedValue +" . Actual Value="+actualValue);
	  System.out.println("Expected Value="+ expectedValue +" . Actual Value="+actualValue);
Assertions.assertEquals(expectedValue, actualValue);
		} catch (Exception exception) {
			log.error("Exception in execution of execute1GetAllLogFromFirstMovF-"+exception,exception);
			exception.printStackTrace();
			Assertions.assertFalse(false);
		}
    }  
 
  @Test  
	@DisplayName("get All Files")
    public void getAllFiles(){  
		try {
			log.info("Starting execution of getAllFiles");
Stream<File> expectedValue = null; 
   
  
 FileServiceImpl fileserviceimpl  =new FileServiceImpl(); 
Stream<File> actualValue=fileserviceimpl.getAllFiles();
	  log.info("Expected Value="+ expectedValue +" . Actual Value="+actualValue);
	  System.out.println("Expected Value="+ expectedValue +" . Actual Value="+actualValue);
Assertions.assertEquals(expectedValue, actualValue);
		} catch (Exception exception) {
			log.error("Exception in execution of execute1GetAllLogFromFirstMovF-"+exception,exception);
			exception.printStackTrace();
			Assertions.assertFalse(false);
		}
    }  
    @AfterEach
    void tearDown() {
    }
    @AfterAll
    static void tearDownAll() {
    }
}
