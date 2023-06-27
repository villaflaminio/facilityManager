package it.flaminiovilla.facilitymanager.model.projection;

import it.flaminiovilla.facilitymanager.model.entity.Gate;
import it.flaminiovilla.facilitymanager.model.entity.Structure;

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