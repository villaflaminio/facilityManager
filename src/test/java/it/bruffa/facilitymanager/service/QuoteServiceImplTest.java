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
//public class QuoteServiceImplTest {
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
//	@DisplayName("update Quote")
//    public void updateQuote(){
//		try {
//			log.info("Starting execution of updateQuote");
//ResponseEntity<Quote> expectedValue = null;
//  Long quoteId=0;
//UpdateQuoteRequest updateQuoteRequest = null;
//
//
// QuoteServiceImpl quoteserviceimpl  =new QuoteServiceImpl();
//ResponseEntity<Quote> actualValue=quoteserviceimpl.updateQuote( quoteId ,updateQuoteRequest);
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
//	@DisplayName("update Quote Exception")
//    public void updateQuoteException(){
//		try {
//			log.info("Starting execution of updateQuoteException");
// Long quoteId=0;
//UpdateQuoteRequest updateQuoteRequest = null;
//
//
// QuoteServiceImpl quoteserviceimpl  =new QuoteServiceImpl();
//quoteserviceimpl.updateQuoteException( quoteId ,updateQuoteRequest);
//Assertions.assertTrue(false);
//		} catch (Exception exception) {
//			log.error("Exception in execution ofupdateQuoteException-"+exception,exception);
//			exception.printStackTrace();
//			Assertions.assertFalse(true);
//		}
//    }
//
//  @Test
//	@DisplayName("add File To Quote")
//    public void addFileToQuote(){
//		try {
//			log.info("Starting execution of addFileToQuote");
//ResponseEntity<Boolean> expectedValue = null;
//  Long quoteId=0;
//MultipartFile file = null;
//
//
// QuoteServiceImpl quoteserviceimpl  =new QuoteServiceImpl();
//ResponseEntity<Boolean> actualValue=quoteserviceimpl.addFileToQuote( quoteId ,file);
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
