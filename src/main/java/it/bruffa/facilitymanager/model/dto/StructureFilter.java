package it.bruffa.facilitymanager.model.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import it.bruffa.facilitymanager.model.entity.CleaningAction;
import it.bruffa.facilitymanager.model.entity.Gate;
import it.bruffa.facilitymanager.model.entity.Maintenance;
import it.bruffa.facilitymanager.model.entity.Quote;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StructureFilter {

    private Long id;
    private Long gateId;
    private Long quoteId;

    private String name;

    private String address;

    private String city;

    private String cap;

    private String province;

    private String country;

    private Double latitude;

    private Double longitude;

    private String description;

    private Boolean isActive;
    private Integer cleaningDuration;

}
