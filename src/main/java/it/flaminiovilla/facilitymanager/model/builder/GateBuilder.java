package it.flaminiovilla.facilitymanager.model.builder;

import it.flaminiovilla.facilitymanager.model.entity.AccessLog;
import it.flaminiovilla.facilitymanager.model.entity.Gate;
import it.flaminiovilla.facilitymanager.model.entity.Structure;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public final class GateBuilder {
    private Long id;
    private @NotNull String name;
    private @NotNull String description;
    private @NotNull Boolean isActive;
    private @NotNull String mqttTopic;
    private Structure structure;
    private List<AccessLog> accessLogs;

    private GateBuilder() {
    }

    public static GateBuilder builder() {
        return new GateBuilder();
    }

    public GateBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public GateBuilder name(String name) {
        this.name = name;
        return this;
    }

    public GateBuilder description(String description) {
        this.description = description;
        return this;
    }

    public GateBuilder isActive(Boolean isActive) {
        this.isActive = isActive;
        return this;
    }

    public GateBuilder mqttTopic(String mqttTopic) {
        this.mqttTopic = mqttTopic;
        return this;
    }

    public GateBuilder structure(Structure structure) {
        this.structure = structure;
        return this;
    }

    public GateBuilder accessLogs(List<AccessLog> accessLogs) {
        this.accessLogs = accessLogs;
        return this;
    }

    public Gate build() {
        Gate gate = new Gate();
        gate.setId(id);
        gate.setName(name);
        gate.setDescription(description);
        gate.setIsActive(isActive);
        gate.setMqttTopic(mqttTopic);
        gate.setStructure(structure);
        gate.setAccessLogs(accessLogs);
        return gate;
    }
}
