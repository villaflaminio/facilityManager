package it.bruffa.facilitymanager.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SignUpRequestDto {
    @NotBlank
    @Schema(description = "First name of the user", example = "Mario")
    private String firstName;
    @NotBlank
    @Schema(description = "Last name of the user", example = "Rossi")
    private String lastName;
    @Schema(description = "Username of the user", example = "MarioRossi")
    private String username;

    @NotBlank
    @Email
    @Schema(description = "Email of the user", example = "mariorossi@gmail.com")
    private String email;

    @NotBlank
    @Schema(description = "Password of the user", example = "password")
    private String password;

    @NotNull
    @Schema(description = "Latitude of the user", example = "45.123456")
    private Double latitude;

    @NotNull
    @Schema(description = "Longitude of the user", example = "9.123456")
    private Double longitude;


}