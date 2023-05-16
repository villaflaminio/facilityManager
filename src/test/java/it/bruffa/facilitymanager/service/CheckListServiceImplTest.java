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
//public class CheckListServiceImplTest {
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
//	@DisplayName("get Check List By Id")
//    public void getCheckListById(){
//		try {
//			log.info("Starting execution of getCheckListById");
//ResponseEntity<CheckList> expectedValue = null;
//  Long checkListId=0;
//
//
// CheckListServiceImpl checklistserviceimpl  =new CheckListServiceImpl();
//ResponseEntity<CheckList> actualValue=checklistserviceimpl.getCheckListById( checkListId);
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
//	@DisplayName("get Check List By Name")
//    public void getCheckListByName(){
//		try {
//			log.info("Starting execution of getCheckListByName");
//ResponseEntity<CheckList> expectedValue = null;
//  String checkListName="";
//
//
// CheckListServiceImpl checklistserviceimpl  =new CheckListServiceImpl();
//ResponseEntity<CheckList> actualValue=checklistserviceimpl.getCheckListByName( checkListName);
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
//	@DisplayName("create Check List")
//    public void createCheckList(){
//		try {
//			log.info("Starting execution of createCheckList");
//ResponseEntity<CheckList> expectedValue = null;
//  CreateCheckListRequest createCheckListRequest = null;
//
//
// CheckListServiceImpl checklistserviceimpl  =new CheckListServiceImpl();
//ResponseEntity<CheckList> actualValue=checklistserviceimpl.createCheckList( createCheckListRequest);
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
//	@DisplayName("update Check List")
//    public void updateCheckList(){
//		try {
//			log.info("Starting execution of updateCheckList");
//ResponseEntity<CheckList> expectedValue = null;
//  Long checkListId=0;
//CreateCheckListRequest createCheckListRequest = null;
//
//
// CheckListServiceImpl checklistserviceimpl  =new CheckListServiceImpl();
//ResponseEntity<CheckList> actualValue=checklistserviceimpl.updateCheckList( checkListId ,createCheckListRequest);
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
//	@DisplayName("delete Check List")
//    public void deleteCheckList(){
//		try {
//			log.info("Starting execution of deleteCheckList");
//ResponseEntity<Boolean> expectedValue = null;
//  Long checkListId=0;
//
//
// CheckListServiceImpl checklistserviceimpl  =new CheckListServiceImpl();
//ResponseEntity<Boolean> actualValue=checklistserviceimpl.deleteCheckList( checkListId);
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
