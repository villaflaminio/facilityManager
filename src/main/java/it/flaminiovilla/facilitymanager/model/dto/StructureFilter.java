package it.flaminiovilla.facilitymanager.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
