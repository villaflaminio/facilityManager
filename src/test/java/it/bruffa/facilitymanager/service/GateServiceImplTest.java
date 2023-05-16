package it.bruffa.facilitymanager.service;

import it.bruffa.facilitymanager.model.dto.request.CreateFeedbackRequest;
import it.bruffa.facilitymanager.model.dto.request.CreateGateRequest;
import it.bruffa.facilitymanager.model.dto.request.CreateStructureRequest;
import it.bruffa.facilitymanager.model.entity.Feedback;
import it.bruffa.facilitymanager.model.entity.Gate;
import it.bruffa.facilitymanager.model.entity.Reservation;
import it.bruffa.facilitymanager.model.entity.Structure;
import it.bruffa.facilitymanager.model.projection.GateInfo;
import it.bruffa.facilitymanager.model.projection.StructureIdInfo;
import it.bruffa.facilitymanager.model.projection.StructureInfo;
import it.bruffa.facilitymanager.repository.*;
import it.bruffa.facilitymanager.service.impl.FeedbackServiceImpl;
import it.bruffa.facilitymanager.service.impl.GateServiceImpl;
import it.bruffa.facilitymanager.service.impl.StructureServiceImpl;
import it.bruffa.facilitymanager.utilities.mqtt.MqttPublishModel;
import org.checkerframework.checker.units.qual.A;
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
import org.springframework.validation.BindingResult;

@ExtendWith(MockitoExtension.class)
public class GateServiceImplTest {

    private static final Logger log = LoggerFactory.getLogger(GateServiceImplTest.class);

    @Mock
    private GateRepository gateRepository;

    @Mock
    private StructureRepository structureRepository;

    @Mock
    private AccessLogService accessLogService;

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    GateServiceImpl gateserviceimpl;
    ProjectionFactory factory = new SpelAwareProxyProjectionFactory();

    @Test
    @DisplayName("get Gate By Id")
    public void getGateById() {
        try {
            log.info("Starting execution of getGateById");
            Gate gate = Instancio.create(Gate.class);
            GateInfo projection = factory.createProjection(GateInfo.class, gate);

            when(gateRepository.getGateById(anyLong())).thenReturn(Optional.of(projection));
            Long gateId = gate.getId();

            ResponseEntity<GateInfo> actualValue = gateserviceimpl.getGateById(gateId);
            Assertions.assertEquals(projection, actualValue.getBody());
        } catch (Exception exception) {
            log.error("Exception in execution of execute1GetAllLogFromFirstMovF-" + exception, exception);
            exception.printStackTrace();
            Assertions.assertFalse(false);
        }
    }

    @Test
    @DisplayName("get Gate By Name")
    public void getGateByName() {
        try {
            log.info("Starting execution of getGateById");
            Gate gate = Instancio.create(Gate.class);
            GateInfo projection = factory.createProjection(GateInfo.class, gate);

            when(gateRepository.findByName(gate.getName())).thenReturn(Optional.of(projection));

            ResponseEntity<GateInfo> actualValue = gateserviceimpl.getGateByName(gate.getName());
            Assertions.assertEquals(projection, actualValue.getBody());
        } catch (Exception exception) {
            log.error("Exception in execution of execute1GetAllLogFromFirstMovF-" + exception, exception);
            exception.printStackTrace();
            Assertions.assertFalse(false);
        }
    }
    @DisplayName("create Gate")
    @Test
    public void createGate() {
        try {
            log.info("Starting execution of createGate");
            CreateGateRequest createGateRequest = Instancio.create(CreateGateRequest.class);

            ResponseEntity<Gate> actualValue = gateserviceimpl.createGate(createGateRequest);

            Assertions.assertEquals(actualValue.getStatusCode(), HttpStatusCode.valueOf(200));
        } catch (Exception exception) {
            log.error("Exception in execution of execute1GetAllLogFromFirstMovF-" + exception, exception);
            exception.printStackTrace();
            Assertions.assertFalse(false);
        }
    }
    @Test
    @DisplayName("update Gate")
    public void updateGate() {
        try {
            log.info("Starting execution of updateGate");
            Gate gate = Instancio.create(Gate.class);
            Long gateId = gate.getId();

            Structure structure = Instancio.create(Structure.class);
            when(structureRepository.findById(anyLong())).thenReturn(Optional.of(structure));
            when(gateRepository.findById(anyLong())).thenReturn(Optional.of(gate));
            CreateGateRequest gateRequest =  Instancio.create(CreateGateRequest.class);


            ResponseEntity<Gate> actualValue = gateserviceimpl.updateGate(gateId, gateRequest);

            Assertions.assertEquals(actualValue.getStatusCode(), HttpStatusCode.valueOf(200));
        } catch (Exception exception) {
            log.error("Exception in execution of execute1GetAllLogFromFirstMovF-" + exception, exception);
            exception.printStackTrace();
            Assertions.assertFalse(false);
        }
    }

    @Test
    @DisplayName("delete Gate")
    public void deleteGate() {
        try {
            log.info("Starting execution of deleteGate");
            ResponseEntity<Boolean> expectedValue = ResponseEntity.ok(true);
            Gate gate = Instancio.create(Gate.class);
            Long gateId = gate.getId();

            when(gateRepository.findById(anyLong())).thenReturn(Optional.of(gate));

            ResponseEntity<Boolean> actualValue = gateserviceimpl.deleteGate(gateId);
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
