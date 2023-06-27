package it.flaminiovilla.facilitymanager.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateQuoteRequest {

    @Schema(description = "Quote description", example = "default")
    private String description;

    @Schema(description = "Quote price", example = "100.0")
    private Double price;

    @Schema(description = "Quote main user", example = "1")
    private Long userId;


    @Min(1)
    @Schema(description = "Time in days", example = "1")
    private Integer time;

    @Schema(description = "Quote file", example = "true")
    private Boolean accepted;
}
