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
//public class ReservationServiceImplTest {
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
//	@DisplayName("get Reservations With Check On Day")
//    public void getReservationsWithCheckOnDay(){
//		try {
//			log.info("Starting execution of getReservationsWithCheckOnDay");
//List<Reservation> expectedValue = null;
//  LocalDate checkOutDate = null;
//
//
// ReservationServiceImpl reservationserviceimpl  =new ReservationServiceImpl();
//List<Reservation> actualValue=reservationserviceimpl.getReservationsWithCheckOnDay( checkOutDate);
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
//	@DisplayName("filter")
//    public void filter(){
//		try {
//			log.info("Starting execution of filter");
//ResponseEntity<Page<Reservation>> expectedValue = null;
//  ReservationFilter probe = null;
//Integer page=0;
//Integer size=0;
//String sortField="";
//String sortDirection="";
//
//
// ReservationServiceImpl reservationserviceimpl  =new ReservationServiceImpl();
//ResponseEntity<Page<Reservation>> actualValue=reservationserviceimpl.filter( probe ,page ,size ,sortField ,sortDirection);
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
//	@DisplayName("get Reservation By Id")
//    public void getReservationById(){
//		try {
//			log.info("Starting execution of getReservationById");
//ResponseEntity<Reservation> expectedValue = null;
//  Long reservationId=0;
//
//
// ReservationServiceImpl reservationserviceimpl  =new ReservationServiceImpl();
//ResponseEntity<Reservation> actualValue=reservationserviceimpl.getReservationById( reservationId);
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
//	@DisplayName("create Reservation")
//    public void createReservation(){
//		try {
//			log.info("Starting execution of createReservation");
//ResponseEntity<Reservation> expectedValue = null;
//  CreateReservationRequest createReservationRequest = null;
//
//
// ReservationServiceImpl reservationserviceimpl  =new ReservationServiceImpl();
//ResponseEntity<Reservation> actualValue=reservationserviceimpl.createReservation( createReservationRequest);
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
//	@DisplayName("delete Reservation")
//    public void deleteReservation(){
//		try {
//			log.info("Starting execution of deleteReservation");
//ResponseEntity<Boolean> expectedValue = null;
//  Long reservationId=0;
//
//
// ReservationServiceImpl reservationserviceimpl  =new ReservationServiceImpl();
//ResponseEntity<Boolean> actualValue=reservationserviceimpl.deleteReservation( reservationId);
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
