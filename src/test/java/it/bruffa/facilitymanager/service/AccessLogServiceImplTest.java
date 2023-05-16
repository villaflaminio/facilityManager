//package it.bruffa.facilitymanager.service;
//import static org.junit.Assert.assertEquals;
//import org.junit.Test;
//import org.apache.log4j.Logger;
//import static org.junit.jupiter.api.Assertions.fail;
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.Test;
//public class AccessLogServiceImplTest {
//
//	private Logger log = Logger.getLogger(this.getClass());
//    @BeforeAll
//    static void initAll() {
//    }
//    @BeforeEach
//    void init() {
//    }
//
//  @Test
//	@DisplayName("filter")
//    public void filter(){
//		try {
//			log.info("Starting execution of filter");
//ResponseEntity<Page<AccessLog>> expectedValue = null;
//  AccessLogFilter probe = null;
//Integer page=0;
//Integer size=0;
//String sortField="";
//String sortDirection="";
//
//
// AccessLogServiceImpl accesslogserviceimpl  =new AccessLogServiceImpl();
//ResponseEntity<Page<AccessLog>> actualValue=accesslogserviceimpl.filter( probe ,page ,size ,sortField ,sortDirection);
//	  log.info("Expected Value="+ expectedValue +" . Actual Value="+actualValue);
//	  System.out.println("Expected Value="+ expectedValue +" . Actual Value="+actualValue);
//Assertions.assertEquals(expectedValue, actualValue);
//		} catch (Exception exception) {
//			log.error("Exception in execution of execute1GetAllLogFromFirstMovF-"+exception,exception);
//			exception.printStackTrace();
//			Assertions.assertFalse(false);
//		}
//    }
//
//  @Test
//	@DisplayName("save")
//    public void save(){
//		try {
//			log.info("Starting execution of save");
//boolean expectedValue=false;
//  Long userId=0;
//Long gateId=0;
//
//
// AccessLogServiceImpl accesslogserviceimpl  =new AccessLogServiceImpl();
//boolean actualValue=accesslogserviceimpl.save( userId ,gateId);
//	  log.info("Expected Value="+ expectedValue +" . Actual Value="+actualValue);
//	  System.out.println("Expected Value="+ expectedValue +" . Actual Value="+actualValue);
//Assertions.assertEquals(expectedValue, actualValue);
//		} catch (Exception exception) {
//			log.error("Exception in execution of execute1GetAllLogFromFirstMovF-"+exception,exception);
//			exception.printStackTrace();
//			Assertions.assertFalse(false);
//		}
//    }
//
//  @Test
//	@DisplayName("save")
//    public void save(){
//		try {
//			log.info("Starting execution of save");
//boolean expectedValue=false;
//  User user = null;
//Gate gate = null;
//
//
// AccessLogServiceImpl accesslogserviceimpl  =new AccessLogServiceImpl();
//boolean actualValue=accesslogserviceimpl.save( user ,gate);
//	  log.info("Expected Value="+ expectedValue +" . Actual Value="+actualValue);
//	  System.out.println("Expected Value="+ expectedValue +" . Actual Value="+actualValue);
//Assertions.assertEquals(expectedValue, actualValue);
//		} catch (Exception exception) {
//			log.error("Exception in execution of execute1GetAllLogFromFirstMovF-"+exception,exception);
//			exception.printStackTrace();
//			Assertions.assertFalse(false);
//		}
//    }
//
//  @Test
//	@DisplayName("get Access Log By Structure Id")
//    public void getAccessLogByStructureId(){
//		try {
//			log.info("Starting execution of getAccessLogByStructureId");
//ResponseEntity<List<AccessLogInfo>> expectedValue = null;
//  Long structureId=0;
//
//
// AccessLogServiceImpl accesslogserviceimpl  =new AccessLogServiceImpl();
//ResponseEntity<List<AccessLogInfo>> actualValue=accesslogserviceimpl.getAccessLogByStructureId( structureId);
//	  log.info("Expected Value="+ expectedValue +" . Actual Value="+actualValue);
//	  System.out.println("Expected Value="+ expectedValue +" . Actual Value="+actualValue);
//Assertions.assertEquals(expectedValue, actualValue);
//		} catch (Exception exception) {
//			log.error("Exception in execution of execute1GetAllLogFromFirstMovF-"+exception,exception);
//			exception.printStackTrace();
//			Assertions.assertFalse(false);
//		}
//    }
//    @AfterEach
//    void tearDown() {
//    }
//    @AfterAll
//    static void tearDownAll() {
//    }
//}
