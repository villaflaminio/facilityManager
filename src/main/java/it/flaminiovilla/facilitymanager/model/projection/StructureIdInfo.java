package it.flaminiovilla.facilitymanager.model.projection;

import it.flaminiovilla.facilitymanager.model.entity.Structure;

/**
 * A Projection for the {@link Structure} entity
 */
public interface StructureIdInfo {
    Long getId();

    String getName();
}