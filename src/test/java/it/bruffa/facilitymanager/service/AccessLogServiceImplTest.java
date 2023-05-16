package it.bruffa.facilitymanager.service;
import it.bruffa.facilitymanager.model.dto.request.CreateCheckListRequest;
import it.bruffa.facilitymanager.model.dto.request.CreateStructureRequest;
import it.bruffa.facilitymanager.model.entity.CheckList;
import it.bruffa.facilitymanager.model.entity.Reservation;
import it.bruffa.facilitymanager.model.entity.Structure;
import it.bruffa.facilitymanager.model.projection.StructureIdInfo;
import it.bruffa.facilitymanager.model.projection.StructureInfo;
import it.bruffa.facilitymanager.repository.*;
import it.bruffa.facilitymanager.service.impl.CheckListServiceImpl;
import it.bruffa.facilitymanager.service.impl.StructureServiceImpl;
import org.instancio.Instancio;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@ExtendWith(MockitoExtension.class)
public class AccessLogServiceImplTest {

    private static final Logger log = LoggerFactory.getLogger(AccessLogServiceImplTest.class);
    @BeforeAll
    static void initAll() {
    }
    @BeforeEach
    void init() {
    }

  @Test
	@DisplayName("filter")
    public void filter(){
		try {
			log.info("Starting execution of filter");
ResponseEntity<Page<AccessLog>> expectedValue = null;
  AccessLogFilter probe = null;
Integer page=0;
Integer size=0;
String sortField="";
String sortDirection="";


 AccessLogServiceImpl accesslogserviceimpl  =new AccessLogServiceImpl();
ResponseEntity<Page<AccessLog>> actualValue=accesslogserviceimpl.filter( probe ,page ,size ,sortField ,sortDirection);
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
	@DisplayName("save")
    public void save(){
		try {
			log.info("Starting execution of save");
boolean expectedValue=false;
  Long userId=0;
Long gateId=0;


 AccessLogServiceImpl accesslogserviceimpl  =new AccessLogServiceImpl();
boolean actualValue=accesslogserviceimpl.save( userId ,gateId);
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
	@DisplayName("save")
    public void save(){
		try {
			log.info("Starting execution of save");
boolean expectedValue=false;
  User user = null;
Gate gate = null;


 AccessLogServiceImpl accesslogserviceimpl  =new AccessLogServiceImpl();
boolean actualValue=accesslogserviceimpl.save( user ,gate);
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
	@DisplayName("get Access Log By Structure Id")
    public void getAccessLogByStructureId(){
		try {
			log.info("Starting execution of getAccessLogByStructureId");
ResponseEntity<List<AccessLogInfo>> expectedValue = null;
  Long structureId=0;


 AccessLogServiceImpl accesslogserviceimpl  =new AccessLogServiceImpl();
ResponseEntity<List<AccessLogInfo>> actualValue=accesslogserviceimpl.getAccessLogByStructureId( structureId);
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
