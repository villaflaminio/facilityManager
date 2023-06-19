package it.bruffa.facilitymanager.model.dto.request;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import it.bruffa.facilitymanager.model.entity.File;
import it.bruffa.facilitymanager.model.entity.Maintenance;
import it.bruffa.facilitymanager.model.entity.Structure;
import it.bruffa.facilitymanager.model.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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
