package it.bruffa.facilitymanager.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SignUpRequestDto {
    @NotBlank
    @Schema(description = "First name of the user", example = "John")
    private String firstName;
    @NotBlank
    @Schema(description = "Last name of the user", example = "Doe")
    private String lastName;
    @Schema(description = "Username of the user", example = "johndoe")
    private String username;

    @NotBlank
    @Email
    @Schema(description = "Email of the user", example = "jon@elis.org")
    private String email;

    @NotBlank
    @Schema(description = "Password of the user", example = "password")
    private String password;

}