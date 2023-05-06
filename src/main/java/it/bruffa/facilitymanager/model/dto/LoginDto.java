package it.bruffa.facilitymanager.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginDto {

    @Schema(description = "User email", example = "bruffa@gmail.com")
    private String email;

    private String username;
    @Schema(description = "User password", example = "password")
    private String password;
}
