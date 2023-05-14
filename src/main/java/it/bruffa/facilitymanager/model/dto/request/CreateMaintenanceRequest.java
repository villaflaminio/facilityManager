package it.bruffa.facilitymanager.model.dto.request;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import it.bruffa.facilitymanager.model.entity.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
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

    @Schema(example = "description")
    private String description;

    @Schema(description = "Date of the Maintenance action", example = "2021-05-05")
    private java.time.LocalDateTime date;

}
