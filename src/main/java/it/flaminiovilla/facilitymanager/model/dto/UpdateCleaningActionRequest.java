package it.flaminiovilla.facilitymanager.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCleaningActionRequest {
    @Schema(description = "Id of the checklist", example = "1")
    private Long checkListId;

    @Schema(description = "Id of the cleaner", example = "1")
    private Long userCleanerId;

    @NotNull
    @Schema(description = "Date of the cleaning action", example = "2021-05-05")
    private LocalDate date;


    @Schema(description = "Note of the cleaning action", example = "Note")
    private String note;

    @Schema(description = "Report of the cleaning action", example = "Report")
    private String report;

    @Max(24)
    @Min(1)
    @Schema(description = "Duration of the cleaning action", example = "1")
    private Integer cleaningDuration;
}
