package it.bruffa.facilitymanager.model.projection;

import it.bruffa.facilitymanager.model.entity.Gate;
import it.bruffa.facilitymanager.model.entity.Structure;

/**
 * A Projection for the {@link Gate} entity
 */
public interface GateInfo {
    Long getId();

    String getName();

    String getDescription();

    Boolean getIsActive();

    String getMqttTopic();

    StructureInfo getStructure();

    /**
     * Projection for {@link Structure}
     */
    interface StructureInfo {
        Long getId();
    }
}