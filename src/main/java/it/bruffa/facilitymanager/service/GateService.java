package it.bruffa.facilitymanager.service;

import it.bruffa.facilitymanager.model.dto.request.CreateGateRequest;
import it.bruffa.facilitymanager.model.entity.Gate;
import it.bruffa.facilitymanager.model.entity.UserPrincipal;
import it.bruffa.facilitymanager.model.projection.GateInfo;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.http.ResponseEntity;

public interface GateService {
    ResponseEntity<GateInfo> getGateById(Long gateId);

    ResponseEntity<GateInfo> getGateByName(String gateName);

    ResponseEntity<Gate> createGate(CreateGateRequest createGateRequest);

    ResponseEntity<Gate> updateGate(Long gateId, CreateGateRequest createGateRequest);

    ResponseEntity<Boolean> deleteGate(Long gateId);

    ResponseEntity<Boolean> openGate(UserPrincipal userPrincipal, Long gateId) throws MqttException;
}
