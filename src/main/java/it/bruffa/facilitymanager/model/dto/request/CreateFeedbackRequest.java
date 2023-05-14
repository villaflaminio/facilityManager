package it.bruffa.facilitymanager.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateFeedbackRequest {

    @NotNull
    @Schema(example = "5", description = "rating must be between 1 and 5")
    private Integer rating;

    @NotNull
    @Schema(example = "1", description = "ratingBeforeIntervention must be between 1 and 5")
    private Integer ratingBeforeIntervention;

    @Schema(example = "1")
    @NotNull
    private Long userId;

    @Schema(example = "1")
    private Long maintenanceId;

    @Schema(example = "1")
    private Long cleaningActionId;

    @Schema(example = "note", description = "note")
    @NotNull
    private String note;


}
