package it.bruffa.facilitymanager.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import it.bruffa.facilitymanager.model.entity.MaintenanceStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaintenanceFilter {

    @Schema(example = "1")
    private Long id;
    @Schema(example = "1")
    private Long structureId;
    @Schema(example = "1")
    private Long userId;
    @Schema(example = "1")
    private Long checkListId;

    @Schema(example = "1")
    private Long QuoteId;

    @Schema(example = "1")
    private Long feedbackId;

    @Schema(description = "Status of the Maintenance action", example = "CREATED")
    private MaintenanceStatus status;

    @Schema(example = "description")
    private String description;

    @Schema(description = "Date of the Maintenance action", example = "2021-05-05")
    private java.time.LocalDateTime date;

    @Schema(description = "Duration of the Maintenance action", example = "1")
    private Integer duration;

    @Schema(description = "Price of the Maintenance action", example = "1")
    private Double cost;

}
