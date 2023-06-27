package it.flaminiovilla.facilitymanager.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


/**
 * This class represents the sign up request.
 */
@Data
public class ChangePassworRequest {
    @NotNull
    @Schema(description = "The old password", example = "123456", required = true)
    private String newPassword;

}
