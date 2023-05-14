package it.bruffa.facilitymanager.model.projection;

import it.bruffa.facilitymanager.model.entity.AccessLog;
import it.bruffa.facilitymanager.model.entity.Gate;
import it.bruffa.facilitymanager.model.entity.Structure;
import it.bruffa.facilitymanager.model.entity.User;

/**
 * A Projection for the {@link AccessLog} entity
 */
public interface AccessLogInfo {
    Long getId();

    Long getTimestamp();

    GateInfo getGate();

    UserInfo getUser();

    /**
     * A Projection for the {@link Gate} entity
     */
    interface GateInfo {
        Long getId();

        String getName();

        String getDescription();

        StructureInfo getStructure();

        /**
         * A Projection for the {@link Structure} entity
         */
        interface StructureInfo {
            Long getId();

            String getName();

            String getAddress();
        }
    }

    /**
     * A Projection for the {@link User} entity
     */
    interface UserInfo {
        Long getId();

        String getFirstName();

        String getLastName();

        String getEmail();
    }
}