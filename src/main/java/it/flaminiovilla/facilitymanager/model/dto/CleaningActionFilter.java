package it.flaminiovilla.facilitymanager.model.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class CleaningActionFilter {

    @Schema(description = "The id of the cleaning action", example = "1")
    private Long id;

    @Schema(description = "The id of the check list", example = "1")
    private Long checkListId;

    @Schema(description = "The id of the user", example = "1")
    private Long userId;

    @Schema(description = "The id of the structure", example = "1")
    private Long structureId;

    @Schema(description = "The date of the cleaning action", example = "2021-01-01")
    private LocalDate date;

    @Schema(description = "The note of the cleaning action", example = "The cleaning action was done")
    private String note;

    @Schema(description = "The report of the cleaning action", example = "The cleaning action was done")
    private String report;

    @Schema(description = "The id of the feedback", example = "1")
    private Integer cleaningDuration;

}
