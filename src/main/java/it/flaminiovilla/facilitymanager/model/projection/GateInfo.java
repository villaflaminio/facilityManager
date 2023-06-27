package it.flaminiovilla.facilitymanager.model.projection;

import it.flaminiovilla.facilitymanager.model.entity.Gate;
import it.flaminiovilla.facilitymanager.model.entity.Structure;

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