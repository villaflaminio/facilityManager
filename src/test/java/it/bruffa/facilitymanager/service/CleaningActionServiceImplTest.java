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
//public class CleaningActionServiceImplTest {
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
//ResponseEntity<Page<CleaningAction>> expectedValue = null;
//  CleaningActionFilter probe = null;
//Integer page=0;
//Integer size=0;
//String sortField="";
//String sortDirection="";
//
//
// CleaningActionServiceImpl cleaningactionserviceimpl  =new CleaningActionServiceImpl();
//ResponseEntity<Page<CleaningAction>> actualValue=cleaningactionserviceimpl.filter( probe ,page ,size ,sortField ,sortDirection);
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
//	@DisplayName("get Cleaning Action By Id")
//    public void getCleaningActionById(){
//		try {
//			log.info("Starting execution of getCleaningActionById");
//ResponseEntity<CleaningActionInfo> expectedValue = null;
//  Long cleaningActionId=0;
//
//
// CleaningActionServiceImpl cleaningactionserviceimpl  =new CleaningActionServiceImpl();
//ResponseEntity<CleaningActionInfo> actualValue=cleaningactionserviceimpl.getCleaningActionById( cleaningActionId);
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
//	@DisplayName("create Cleaning Action")
//    public void createCleaningAction(){
//		try {
//			log.info("Starting execution of createCleaningAction");
//ResponseEntity<CleaningAction> expectedValue = null;
//  CreateCleaningActionRequest createCleaningActionRequest = null;
//
//
// CleaningActionServiceImpl cleaningactionserviceimpl  =new CleaningActionServiceImpl();
//ResponseEntity<CleaningAction> actualValue=cleaningactionserviceimpl.createCleaningAction( createCleaningActionRequest);
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
//	@DisplayName("update Cleaning Action")
//    public void updateCleaningAction(){
//		try {
//			log.info("Starting execution of updateCleaningAction");
//ResponseEntity<CleaningAction> expectedValue = null;
//  Long cleaningActionId=0;
//UpdateCleaningActionRequest updateCleaningActionRequest = null;
//
//
// CleaningActionServiceImpl cleaningactionserviceimpl  =new CleaningActionServiceImpl();
//ResponseEntity<CleaningAction> actualValue=cleaningactionserviceimpl.updateCleaningAction( cleaningActionId ,updateCleaningActionRequest);
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
//	@DisplayName("delete Cleaning Action")
//    public void deleteCleaningAction(){
//		try {
//			log.info("Starting execution of deleteCleaningAction");
//ResponseEntity<Boolean> expectedValue = null;
//  Long cleaningActionId=0;
//
//
// CleaningActionServiceImpl cleaningactionserviceimpl  =new CleaningActionServiceImpl();
//ResponseEntity<Boolean> actualValue=cleaningactionserviceimpl.deleteCleaningAction( cleaningActionId);
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
//	@DisplayName("add Picture To Cleaning Action")
//    public void addPictureToCleaningAction(){
//		try {
//			log.info("Starting execution of addPictureToCleaningAction");
//ResponseEntity<Boolean> expectedValue = null;
//  Long cleaningActionId=0;
//MultipartFile file = null;
//
//
// CleaningActionServiceImpl cleaningactionserviceimpl  =new CleaningActionServiceImpl();
//ResponseEntity<Boolean> actualValue=cleaningactionserviceimpl.addPictureToCleaningAction( cleaningActionId ,file);
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
//	@DisplayName("remove Picture From Cleaning Action")
//    public void removePictureFromCleaningAction(){
//		try {
//			log.info("Starting execution of removePictureFromCleaningAction");
//ResponseEntity<Boolean> expectedValue = null;
//  Long pictureId=0;
//
//
// CleaningActionServiceImpl cleaningactionserviceimpl  =new CleaningActionServiceImpl();
//ResponseEntity<Boolean> actualValue=cleaningactionserviceimpl.removePictureFromCleaningAction( pictureId);
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
//	@DisplayName("get Pictures From Cleaning Action")
//    public void getPicturesFromCleaningAction(){
//		try {
//			log.info("Starting execution of getPicturesFromCleaningAction");
//ResponseEntity<List<ResponseFile>> expectedValue = null;
//  Long cleaningActionId=0;
//
//
// CleaningActionServiceImpl cleaningactionserviceimpl  =new CleaningActionServiceImpl();
//ResponseEntity<List<ResponseFile>> actualValue=cleaningactionserviceimpl.getPicturesFromCleaningAction( cleaningActionId);
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
//	@DisplayName("assign Cleaning Action")
//    public void assignCleaningAction(){
//		try {
//			log.info("Starting execution of assignCleaningAction");
//List<CleaningAction> expectedValue = null;
//  LocalDate checkOutDate = null;
//
//
// CleaningActionServiceImpl cleaningactionserviceimpl  =new CleaningActionServiceImpl();
//List<CleaningAction> actualValue=cleaningactionserviceimpl.assignCleaningAction( checkOutDate);
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
