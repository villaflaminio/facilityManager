package it.flaminiovilla.facilitymanager.model.projection;

import it.flaminiovilla.facilitymanager.model.entity.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * A Projection for the {@link CleaningAction} entity
 */
public interface CleaningActionInfo {
    Long getId();

    LocalDate getDate();

    String getNote();

    String getReport();

    CheckListInfo getCheckList();

    UserInfo getUser();

    StructureInfo getStructure();

    FeedbackInfo getFeedback();

    Integer getCleaningDuration();

    /**
     * A Projection for the {@link CheckList} entity
     */
    interface CheckListInfo {
        Long getId();

        String getName();

        String getDescription();

        List<String> getItems();
    }

    /**
     * A Projection for the {@link User} entity
     */
    interface UserInfo {
        Long getId();

        String getFirstName();

        String getLastName();

        String getEmail();

        Double getLatitude();

        Double getLongitude();

        Boolean getEnable();
    }

    /**
     * A Projection for the {@link Structure} entity
     */
    interface StructureInfo {
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
    }

    /**
     * A Projection for the {@link Feedback} entity
     */
    interface FeedbackInfo {
        Long getId();

        Integer getRating();

        Integer getRatingBeforeIntervention();

        LocalDateTime getDate();

        String getNote();

        UserInfo getUser();

        /**
         * A Projection for the {@link User} entity
         */
        interface UserInfo {
            Long getId();
        }
    }
}