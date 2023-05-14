package it.bruffa.facilitymanager.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegisterUserRequestDto {
    @NotBlank
    @Schema(description = "First name of the user", example = "Mario")
    private String firstName;

    @NotBlank
    @Schema(description = "Last name of the user", example = "Rossi")
    private String lastName;

    @NotBlank
    @Email
    @Schema(description = "Email of the user", example = "mariorossi@gmail.com")
    private String email;

    @NotNull
    @Schema(description = "Latitude of the user", example = "45.123456")
    private Double latitude;

    @NotNull
    @Schema(description = "Longitude of the user", example = "9.123456")
    private Double longitude;

    @NotBlank
    @Schema(description = "Role of the user", example = "ROLE_USER / ROLE_ADMIN / ROLE_CLEANER / ROLE_MAINTAINER")
    private String role;
}