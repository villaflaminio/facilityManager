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
//public class GateServiceImplTest {
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
//	@DisplayName("get Gate By Id")
//    public void getGateById(){
//		try {
//			log.info("Starting execution of getGateById");
//ResponseEntity<GateInfo> expectedValue = null;
//  Long gateId=0;
//
//
// GateServiceImpl gateserviceimpl  =new GateServiceImpl();
//ResponseEntity<GateInfo> actualValue=gateserviceimpl.getGateById( gateId);
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
//	@DisplayName("get Gate By Name")
//    public void getGateByName(){
//		try {
//			log.info("Starting execution of getGateByName");
//ResponseEntity<GateInfo> expectedValue = null;
//  String gateName="";
//
//
// GateServiceImpl gateserviceimpl  =new GateServiceImpl();
//ResponseEntity<GateInfo> actualValue=gateserviceimpl.getGateByName( gateName);
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
//	@DisplayName("create Gate")
//    public void createGate(){
//		try {
//			log.info("Starting execution of createGate");
//ResponseEntity<Gate> expectedValue = null;
//  CreateGateRequest createGateRequest = null;
//
//
// GateServiceImpl gateserviceimpl  =new GateServiceImpl();
//ResponseEntity<Gate> actualValue=gateserviceimpl.createGate( createGateRequest);
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
//	@DisplayName("update Gate")
//    public void updateGate(){
//		try {
//			log.info("Starting execution of updateGate");
//ResponseEntity<Gate> expectedValue = null;
//  Long gateId=0;
//CreateGateRequest createGateRequest = null;
//
//
// GateServiceImpl gateserviceimpl  =new GateServiceImpl();
//ResponseEntity<Gate> actualValue=gateserviceimpl.updateGate( gateId ,createGateRequest);
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
//	@DisplayName("delete Gate")
//    public void deleteGate(){
//		try {
//			log.info("Starting execution of deleteGate");
//ResponseEntity<Boolean> expectedValue = null;
//  Long gateId=0;
//
//
// GateServiceImpl gateserviceimpl  =new GateServiceImpl();
//ResponseEntity<Boolean> actualValue=gateserviceimpl.deleteGate( gateId);
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
//	@DisplayName("open Gate")
//    public void openGate(){
//		try {
//			log.info("Starting execution of openGate");
//ResponseEntity<Boolean> expectedValue = null;
//  UserPrincipal userPrincipal = null;
//Long gateId=0;
//
//
// GateServiceImpl gateserviceimpl  =new GateServiceImpl();
//ResponseEntity<Boolean> actualValue=gateserviceimpl.openGate( userPrincipal ,gateId);
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
//	@DisplayName("open Gate Exception")
//    public void openGateException(){
//		try {
//			log.info("Starting execution of openGateException");
// UserPrincipal userPrincipal = null;
//Long gateId=0;
//
//
// GateServiceImpl gateserviceimpl  =new GateServiceImpl();
//gateserviceimpl.openGateException( userPrincipal ,gateId);
//Assertions.assertTrue(false);
//		} catch (Exception exception) {
//			log.error("Exception in execution ofopenGateException-"+exception,exception);
//			exception.printStackTrace();
//			Assertions.assertFalse(true);
//		}
//    }
//
//  @Test
//	@DisplayName("publish Message Exception")
//    public void publishMessageException(){
//		try {
//			log.info("Starting execution of publishMessageException");
// MqttPublishModel messagePublishModel = null;
//BindingResult bindingResult = null;
//
//
// GateServiceImpl gateserviceimpl  =new GateServiceImpl();
//gateserviceimpl.publishMessageException( messagePublishModel ,bindingResult);
//Assertions.assertTrue(false);
//		} catch (Exception exception) {
//			log.error("Exception in execution ofpublishMessageException-"+exception,exception);
//			exception.printStackTrace();
//			Assertions.assertFalse(true);
//		}
//    }
//
//  @Test
//	@DisplayName("publish Message")
//    public void publishMessage(){
//		try {
//			log.info("Starting execution of publishMessage");
// MqttPublishModel messagePublishModel = null;
//BindingResult bindingResult = null;
//
//
// GateServiceImpl gateserviceimpl  =new GateServiceImpl();
//gateserviceimpl.publishMessage( messagePublishModel ,bindingResult);
//Assertions.assertTrue(true);
//		} catch (Exception exception) {
//			log.error("Exception in execution ofpublishMessage-"+exception,exception);
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
