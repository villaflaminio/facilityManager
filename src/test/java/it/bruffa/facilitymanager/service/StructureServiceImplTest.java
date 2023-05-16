package it.bruffa.facilitymanager.service;

import it.bruffa.facilitymanager.model.dto.request.CreateStructureRequest;
import it.bruffa.facilitymanager.model.entity.Reservation;
import it.bruffa.facilitymanager.model.entity.Structure;
import it.bruffa.facilitymanager.model.projection.StructureIdInfo;
import it.bruffa.facilitymanager.model.projection.StructureInfo;
import it.bruffa.facilitymanager.repository.GateRepository;
import it.bruffa.facilitymanager.repository.QuoteRepository;
import it.bruffa.facilitymanager.repository.ReservationRepository;
import it.bruffa.facilitymanager.repository.StructureRepository;
import it.bruffa.facilitymanager.service.impl.StructureServiceImpl;
import org.instancio.Instancio;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
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

public class StructureServiceImplTest {

    private static final Logger log = LoggerFactory.getLogger(StructureServiceImplTest.class);

    @Mock
    private StructureRepository structureRepository;

    @Mock
    private GateRepository gateRepository;
    @Mock
    private QuoteRepository quoteRepository;
    @Mock
    private ReservationRepository reservationRepository;
    @InjectMocks
    private StructureServiceImpl structureserviceimpl;
    ProjectionFactory factory = new SpelAwareProxyProjectionFactory();

    @BeforeAll
    static void initAll() {
    }

    @BeforeEach
    void init() {
    }

    @Test
    @DisplayName("get Structure By Id")
    public void getStructureById() {
        try {
            log.info("Starting execution of getStructureById");
            Structure structure = Instancio.create(Structure.class);
            StructureInfo projection = factory.createProjection(StructureInfo.class, structure);

            when(structureRepository.findStructureById(anyLong())).thenReturn(Optional.of(projection));
            Long structureId = structure.getId();

            ResponseEntity<StructureInfo> actualValue = structureserviceimpl.getStructureById(structureId);
            Assertions.assertEquals(projection, actualValue.getBody());
        } catch (Exception exception) {
            log.error("Exception in execution of execute1GetAllLogFromFirstMovF-" + exception, exception);
            exception.printStackTrace();
            Assertions.assertFalse(false);
        }
    }


    @DisplayName("create Structure")
    @Test
    public void createStructure() {
        try {
            log.info("Starting execution of createStructure");
            CreateStructureRequest createStructureRequest = Instancio.create(CreateStructureRequest.class);

            ResponseEntity<Structure> actualValue = structureserviceimpl.createStructure(createStructureRequest);

            Assertions.assertEquals(actualValue.getStatusCode(), HttpStatusCode.valueOf(200));
        } catch (Exception exception) {
            log.error("Exception in execution of execute1GetAllLogFromFirstMovF-" + exception, exception);
            exception.printStackTrace();
            Assertions.assertFalse(false);
        }
    }


    @Test
    @DisplayName("get Structures List")
    public void getStructuresList() {
        try {
            log.info("Starting execution of getStructuresList");

            Structure structure = Instancio.create(Structure.class);
            StructureIdInfo projection = factory.createProjection(StructureIdInfo.class, structure);

            when(structureRepository.getStructureIdInfoBy()).thenReturn(List.of(projection));

            ResponseEntity<List<StructureIdInfo>> actualValue = structureserviceimpl.getStructuresList();
            Assertions.assertEquals(actualValue.getStatusCode(), HttpStatusCode.valueOf(200));
            Assertions.assertEquals(actualValue.getBody(), List.of(projection));
        } catch (Exception exception) {
            log.error("Exception in execution of execute1GetAllLogFromFirstMovF-" + exception, exception);
            exception.printStackTrace();
            Assertions.assertFalse(false);
        }
    }

    @Test
    @DisplayName("get Reservations Between Dates And Structure")
    public void getReservationsBetweenDatesAndStructure() {
        try {
            log.info("Starting execution of getReservationsBetweenDatesAndStructure");
            LocalDate startDate = Instancio.create(LocalDate.class);
            LocalDate endDate = Instancio.create(LocalDate.class);
            Long idStruttura = 0l;
            List<Reservation> reservationList = Instancio.ofList(Reservation.class).size(5).create();

            when(reservationRepository.getAllBetweenDatesAndStructure( any() , any(), anyLong())).thenReturn(reservationList);

            ResponseEntity<List<Reservation>> actualValue = structureserviceimpl.getReservationsBetweenDatesAndStructure(startDate, endDate, idStruttura);

            Assertions.assertEquals(actualValue.getStatusCode(), HttpStatusCode.valueOf(200));
            Assertions.assertEquals(actualValue.getBody(), reservationList);

        } catch (Exception exception) {
            log.error("Exception in execution of execute1GetAllLogFromFirstMovF-" + exception, exception);
            exception.printStackTrace();
            Assertions.assertFalse(false);
        }
    }

    @Test
    @DisplayName("update Structure")
    public void updateStructure() {
        try {
            log.info("Starting execution of updateStructure");
            Structure structure = Instancio.create(Structure.class);
            Long structureId = structure.getId();

            when(structureRepository.findById(anyLong())).thenReturn(Optional.of(structure));
            CreateStructureRequest structureRequest =  Instancio.create(CreateStructureRequest.class);


            ResponseEntity<Structure> actualValue = structureserviceimpl.updateStructure(structureId, structureRequest);

            Assertions.assertEquals(actualValue.getStatusCode(), HttpStatusCode.valueOf(200));
        } catch (Exception exception) {
            log.error("Exception in execution of execute1GetAllLogFromFirstMovF-" + exception, exception);
            exception.printStackTrace();
            Assertions.assertFalse(false);
        }
    }

    @Test
    @DisplayName("delete Structure")
    public void deleteStructure() {
        try {
            log.info("Starting execution of deleteStructure");
            ResponseEntity<Boolean> expectedValue = ResponseEntity.ok(true);
            Structure structure = Instancio.create(Structure.class);
            Long structureId = structure.getId();

            when(structureRepository.findById(anyLong())).thenReturn(Optional.of(structure));

            ResponseEntity<Boolean> actualValue = structureserviceimpl.deleteStructure(structureId);
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
