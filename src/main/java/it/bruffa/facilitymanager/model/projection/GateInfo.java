package it.bruffa.facilitymanager.model.projection;

import it.bruffa.facilitymanager.model.entity.Gate;

/**
 * A Projection for the {@link Gate} entity
 */
public interface GateInfo {
    Long getId();

    String getName();

    String getDescription();

    Boolean getIsActive();

    String getMqttTopic();
}