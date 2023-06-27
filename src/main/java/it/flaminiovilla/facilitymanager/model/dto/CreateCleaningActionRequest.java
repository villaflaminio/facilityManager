package it.flaminiovilla.facilitymanager.model.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateCleaningActionRequest {

    @NotNull
    @Schema(description = "Id of the checklist", example = "1")
    private Long checkListId;

    @NotNull
    @Schema(description = "Id of the cleaner", example = "1")
    private Long userCleanerId;

    @NotNull
    @Schema(description = "Id of the structure", example = "1")
    private Long structureId;

    @NotNull
    @Schema(description = "Date of the cleaning action", example = "2021-05-05")
    private LocalDate date;


    @Schema(description = "Note of the cleaning action", example = "Note")
    private String note;

    @Schema(description = "Report of the cleaning action", example = "Report")
    private String report;

    @Max(24)
    @Min(0)
    @Schema(description = "Duration of the cleaning action, if 0 use the default value specified in structure", example = "0")
    private Integer cleaningDuration;
}
