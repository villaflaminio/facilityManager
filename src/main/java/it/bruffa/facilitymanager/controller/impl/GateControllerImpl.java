package it.bruffa.facilitymanager.controller.impl;

import io.swagger.v3.oas.annotations.tags.Tag;
import it.bruffa.facilitymanager.controller.GateController;
import it.bruffa.facilitymanager.model.dto.request.CreateGateRequest;
import it.bruffa.facilitymanager.model.entity.Gate;
import it.bruffa.facilitymanager.model.entity.UserPrincipal;
import it.bruffa.facilitymanager.model.exception.ItemNotFoundException;
import it.bruffa.facilitymanager.model.projection.GateInfo;
import it.bruffa.facilitymanager.service.GateService;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/gates")
@Tag(name = "gate", description = "The gates APIs")
public class GateControllerImpl implements GateController {
    @Autowired
    private GateService gateService;

    /**
     * Get all gates
     * @return
     */
    @Override
    public ResponseEntity<GateInfo> getGateById(Long gateId) throws ItemNotFoundException {
        return gateService.getGateById(gateId);
    }

    /**
     * Get gate by id
     * @param gateId
     * @return
     */
    @Override
    public ResponseEntity<GateInfo> getGateByName(String gateName) throws ItemNotFoundException {
        return gateService.getGateByName(gateName);
    }

    /**
     * Get gate by id
     * @param gateId
     * @return
     */
    @Override
    public ResponseEntity<Gate> createGate(CreateGateRequest createGateRequest) throws Exception {
        return gateService.createGate(createGateRequest);
    }

    /**
     * Update gate
     * @param gateId
     * @param createGateRequest
     * @return
     * @throws ItemNotFoundException
     */
    @Override
    public ResponseEntity<Gate> updateGate(Long gateId, CreateGateRequest createGateRequest) throws ItemNotFoundException, Exception {
        return gateService.updateGate(gateId, createGateRequest);
    }

    /**
     * Delete gate
     * @param gateId
     * @return
     * @throws ItemNotFoundException
     */
    @Override
    public ResponseEntity<Boolean> deleteGate(Long gateId) throws ItemNotFoundException {
        return gateService.deleteGate(gateId);
    }

    /**
     * Open gate
     * @param gateId
     * @return
     * @throws ItemNotFoundException
     */
    @Override
    public ResponseEntity<Boolean> openGate(UserPrincipal userPrincipal, Long gateId) throws ItemNotFoundException, MqttException {
        return gateService.openGate(userPrincipal,gateId);
    }
}
