package it.bruffa.facilitymanager.model.projection;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * A Projection for the {@link it.bruffa.facilitymanager.model.entity.CleaningAction} entity
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
     * A Projection for the {@link it.bruffa.facilitymanager.model.entity.CheckList} entity
     */
    interface CheckListInfo {
        Long getId();

        String getName();

        String getDescription();

        List<String> getItems();
    }

    /**
     * A Projection for the {@link it.bruffa.facilitymanager.model.entity.User} entity
     */
    interface UserInfo {
        Long getId();

        String getFirstName();

        String getLastName();

        String getEmail();

        Double getLatitude();

        Double getLongitude();

        Boolean isEnable();
    }

    /**
     * A Projection for the {@link it.bruffa.facilitymanager.model.entity.Structure} entity
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

        Boolean isIsActive();

        Integer getCleaningDuration();
    }

    /**
     * A Projection for the {@link it.bruffa.facilitymanager.model.entity.Feedback} entity
     */
    interface FeedbackInfo {
        Long getId();

        Integer getRating();

        Integer getRatingBeforeIntervention();

        LocalDateTime getDate();

        String getNote();

        UserInfo getUser();

        /**
         * A Projection for the {@link it.bruffa.facilitymanager.model.entity.User} entity
         */
        interface UserInfo {
            Long getId();
        }
    }
}