package it.flaminiovilla.facilitymanager.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateStructureRequest {
    @NotNull
    @Schema(description = "Name of the structure", example = "Home Bruffa")
    private String name;

    @NotNull
    @Schema(description = "Address of the structure", example = "Via Roma 1")
    private String address;

    @NotNull
    @Schema(description = "City of the structure", example = "Roma")
    private String city;

    @NotNull
    @Schema(description = "Cap of the structure", example = "00159")
    private String cap;

    @NotNull
    @Schema(description = "Province of the structure", example = "RM")
    private String province;

    @NotNull
    @Schema(description = "Country of the structure", example = "Italy")
    private String country;

    @NotNull
    @Schema(description = "Latitude of the structure", example = "41.9109")
    private Double latitude;

    @NotNull
    @Schema(description = "Longitude of the structure", example = "12.4818")
    private Double longitude;

    @Schema(description = "Description of the structure", example = "Home Bruffa is a beautiful place")
    private String description;

    @NotNull
    @Schema(description = "Is the structure active?", example = "true")
    private Boolean isActive;


    @Max(24)
    @Min(1)
    @Schema(description = "Duration of the cleaning action", example = "2")
    private Integer cleaningDuration;

    @Override
    public String toString() {
        return "CreateStructureRequest{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", cap='" + cap + '\'' +
                ", province='" + province + '\'' +
                ", country='" + country + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", description='" + description + '\'' +
                ", isActive=" + isActive +
                ", cleaningDuration=" + cleaningDuration +
                '}';
    }
}
