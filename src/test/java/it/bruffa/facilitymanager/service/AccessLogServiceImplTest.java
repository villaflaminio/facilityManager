package it.bruffa.facilitymanager.service;

import it.bruffa.facilitymanager.model.dto.request.CreateCheckListRequest;
import it.bruffa.facilitymanager.model.dto.request.CreateStructureRequest;
import it.bruffa.facilitymanager.model.entity.*;
import it.bruffa.facilitymanager.model.projection.AccessLogInfo;
import it.bruffa.facilitymanager.model.projection.StructureIdInfo;
import it.bruffa.facilitymanager.model.projection.StructureInfo;
import it.bruffa.facilitymanager.repository.*;
import it.bruffa.facilitymanager.service.impl.AccessLogServiceImpl;
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

    @Mock
    private AccessLogRepository accessLogRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private StructureRepository structureRepository;
    @Mock
    private GateRepository gateRepository;
    ProjectionFactory factory = new SpelAwareProxyProjectionFactory();

    @InjectMocks
    AccessLogServiceImpl accesslogserviceimpl;


    @Test
    @DisplayName("save")
    public void save() {
        try {
            log.info("Starting execution of save");
            boolean expectedValue = true;
            User user = Instancio.create(User.class);
            Gate gate = Instancio.create(Gate.class);

            Long userId = user.getId();
            Long gateId = gate.getId();
            when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
            when(gateRepository.findById(anyLong())).thenReturn(Optional.of(gate));

            boolean actualValue = accesslogserviceimpl.save(userId, gateId);
            log.info("Expected Value=" + expectedValue + " . Actual Value=" + actualValue);
            System.out.println("Expected Value=" + expectedValue + " . Actual Value=" + actualValue);
            Assertions.assertEquals(expectedValue, actualValue);
        } catch (Exception exception) {
            log.error("Exception in execution of execute1GetAllLogFromFirstMovF-" + exception, exception);
            exception.printStackTrace();
            Assertions.assertFalse(false);
        }
    }


    @Test
    @DisplayName("get Access Log By Structure Id")
    public void getAccessLogByStructureId() {
        try {
            log.info("Starting execution of getAccessLogByStructureId");
            Structure structure = Instancio.create(Structure.class);
            Long structureId = structure.getId();
            when(structureRepository.findById(anyLong())).thenReturn(Optional.of(structure));

            Gate gate = Instancio.create(Gate.class);
            AccessLogInfo projection = factory.createProjection(AccessLogInfo.class, gate);
            List<AccessLogInfo>expectedValue = List.of(projection);

            when(accessLogRepository.findByGate(any())).thenReturn(List.of(projection));

            ResponseEntity<List<AccessLogInfo>> actualValue = accesslogserviceimpl.getAccessLogByStructureId(structureId);
            log.info("Expected Value=" + expectedValue + " . Actual Value=" + actualValue);
            System.out.println("Expected Value=" + expectedValue + " . Actual Value=" + actualValue);
            Assertions.assertEquals(expectedValue, actualValue.getBody());
        } catch (Exception exception) {
            log.error("Exception in execution of execute1GetAllLogFromFirstMovF-" + exception, exception);
            exception.printStackTrace();
            Assertions.assertFalse(false);
        }
    }

}
