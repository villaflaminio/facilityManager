package it.bruffa.facilitymanager.service.impl;

import it.bruffa.facilitymanager.model.builder.GateBuilder;
import it.bruffa.facilitymanager.model.builder.MqttPublishModelBuilder;
import it.bruffa.facilitymanager.model.dto.request.CreateGateRequest;
import it.bruffa.facilitymanager.model.entity.*;
import it.bruffa.facilitymanager.model.projection.GateInfo;
import it.bruffa.facilitymanager.repository.GateRepository;
import it.bruffa.facilitymanager.repository.StructureRepository;
import it.bruffa.facilitymanager.repository.UserRepository;
import it.bruffa.facilitymanager.service.AccessLogService;
import it.bruffa.facilitymanager.service.GateService;
import it.bruffa.facilitymanager.utilities.PropertiesHelper;
import it.bruffa.facilitymanager.utilities.mqtt.Mqtt;
import it.bruffa.facilitymanager.utilities.mqtt.MqttPublishModel;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public class GateServiceImpl implements GateService {

    @Autowired
    private GateRepository gateRepository;

    @Autowired
    private StructureRepository structureRepository;

    @Autowired
    private AccessLogService accessLogService;

    @Autowired

    private static final Logger logger = LoggerFactory.getLogger(GateService.class);
    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<GateInfo> getGateById(Long gateId) {
        logger.info("getGateById() called with id: {}", gateId);
        GateInfo gate = gateRepository.getGateById(gateId).orElseThrow(() -> new RuntimeException("Gate not found"));
        return ResponseEntity.ok(gate);
    }

    @Override
    public ResponseEntity<GateInfo> getGateByName(String gateName) {
        logger.info("getGateByName() called with name: {}", gateName);
        GateInfo gate = gateRepository.findByName(gateName).orElseThrow(() -> new RuntimeException("Gate not found"));
        return ResponseEntity.ok(gate);
    }

    @Override
    public ResponseEntity<Gate> createGate(CreateGateRequest createGateRequest) {
        try {
            logger.info("createGate() called with CheckList: {}", createGateRequest);
            Structure structure = structureRepository.findById(createGateRequest.getStructureId()).orElseThrow(() -> new RuntimeException("Structure not found"));


            Gate newGate = GateBuilder.builder()
                    .name(createGateRequest.getName())
                    .description(createGateRequest.getDescription())
                    .isActive(createGateRequest.getIsActive())
                    .mqttTopic(createGateRequest.getMqttTopic())
                    .structure(structure)
                    .build();

            Gate savedGate = gateRepository.save(newGate);
            structure.setGate(savedGate);
            structureRepository.save(structure);
            return ResponseEntity.ok(savedGate);

        } catch (Exception e) {
            logger.error("Error in createGate() method: {}", e.getMessage());
            throw e;
        } finally {
            logger.debug("Exit from createGate() method");
        }

    }

    @Override
    public ResponseEntity<Gate> updateGate(Long gateId, CreateGateRequest createGateRequest) {
        try {
            logger.info("updateGate() called with CheckList: {}", createGateRequest);
            Gate gateToUpdate = gateRepository.findById(gateId).orElseThrow(() -> new RuntimeException("Gate not found"));

            // Copy only not null properties from the request to the CleaningAction
            PropertiesHelper.copyNonNullProperties(createGateRequest, gateToUpdate);

            boolean isStructureChanged = false;
            if (createGateRequest.getStructureId() != null) {
                gateToUpdate.setStructure(structureRepository.findById(createGateRequest.getStructureId()).orElseThrow(() -> new RuntimeException("Structure not found")));
                isStructureChanged = true;
            }

            Gate savedGate = gateRepository.save(gateToUpdate);
            if (isStructureChanged) {
                Structure structure = structureRepository.findById(createGateRequest.getStructureId()).orElseThrow(() -> new RuntimeException("Structure not found"));
                structure.setGate(savedGate);
                structureRepository.save(structure);
            }

            return ResponseEntity.ok(savedGate);

        } catch (Exception e) {
            logger.error("Error in updateGate() method: {}", e.getMessage());
            throw e;
        } finally {
            logger.debug("Exit from updateGate() method");
        }
    }

    @Override
    public ResponseEntity<Boolean> deleteGate(Long gateId) {
        try {
            logger.info("deleteGate() called with id: {}", gateId);
            Gate gateToDelete = gateRepository.findById(gateId).orElseThrow(() -> new RuntimeException("Gate not found"));
            gateRepository.delete(gateToDelete);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            logger.error("Error in deleteGate() method: {}", e.getMessage());
            throw e;
        } finally {
            logger.debug("Exit from deleteGate() method");
        }
    }

    @Override
    public ResponseEntity<Boolean> openGate(UserPrincipal userPrincipal, Long gateId) throws MqttException {
        try {
            Gate gate = gateRepository.findById(gateId).orElseThrow(() -> new RuntimeException("Gate not found"));
            User user = userRepository.findById(userPrincipal.getId()).orElseThrow(() -> new RuntimeException("User not found"));

            MqttPublishModel mqttPublishModelBuilder = MqttPublishModelBuilder.builder()
                    .topic(gate.getMqttTopic())
                    .message("OPEN/" + gate.getId() + "/" + gate.getStructure().getName())
                    .qos(2)
                    .retained(false)
                    .build();

            publishMessage(mqttPublishModelBuilder, null);

            accessLogService.save(user, gate);

            return ResponseEntity.ok(true);
        } catch (Exception e) {
            logger.error("Error in openGate() method: {}", e.getMessage());
            throw e;
        }
    }


    public void publishMessage(MqttPublishModel messagePublishModel, BindingResult bindingResult) throws org.eclipse.paho.client.mqttv3.MqttException {

        MqttMessage mqttMessage = new MqttMessage(messagePublishModel.getMessage().getBytes());
        mqttMessage.setQos(messagePublishModel.getQos());
        mqttMessage.setRetained(messagePublishModel.getRetained());

        Mqtt.getInstance().publish(messagePublishModel.getTopic(), mqttMessage);
    }
}
