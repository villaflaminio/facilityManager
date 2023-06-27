package it.flaminiovilla.facilitymanager.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import it.flaminiovilla.facilitymanager.model.entity.MaintenanceStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateMaintenanceRequest {

    @Schema(example = "1")
    private Long structureId;
    @Schema(example = "1")
    private Long userId;
    @Schema(example = "1")
    private Long checkListId;

    @Schema(description = "Status of the Maintenance action", example = "CREATED")
    private MaintenanceStatus status;

    @Schema(example = "description")
    private String description;

    @Schema(description = "Date of the Maintenance action", example = "2021-05-05")
    private java.time.LocalDateTime date;

}
