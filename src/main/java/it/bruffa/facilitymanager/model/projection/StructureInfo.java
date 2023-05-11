package it.bruffa.facilitymanager.model.projection;

import it.bruffa.facilitymanager.model.entity.Gate;
import it.bruffa.facilitymanager.model.entity.Structure;

/**
 * A Projection for the {@link Structure} entity
 */
public interface StructureInfo {
    Long getId();

    String getName();

    String getAddress();

    String getCity();

    String getCap();

    String getProvince();

    String getCountry();

    Double getLatitude();

    Double getLongitude();

    String getDescription();

    Boolean getIsActive();

    Integer getCleaningDuration();

    GateInfo getGate();

    /**
     * A Projection for the {@link Gate} entity
     */
    interface GateInfo {
        Long getId();

        String getName();
    }
}