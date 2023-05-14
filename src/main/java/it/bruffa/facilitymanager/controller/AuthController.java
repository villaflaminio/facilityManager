package it.bruffa.facilitymanager.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import it.bruffa.facilitymanager.model.dto.AuthResponseDTO;
import it.bruffa.facilitymanager.model.dto.LoginDto;
import it.bruffa.facilitymanager.model.dto.TokenRefreshResponseDto;
import it.bruffa.facilitymanager.model.dto.request.RegisterUserRequestDto;
import it.bruffa.facilitymanager.model.dto.request.SignUpRequestDto;
import it.bruffa.facilitymanager.model.entity.User;
import it.bruffa.facilitymanager.model.exception.ApiError;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface AuthController{

    @Operation(summary = "Login", description = "Login", tags = {"auth"})
    @PostMapping("/login")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully logged in"),
            @ApiResponse(responseCode = "403", description = " Forbidden - The user is not authorized to access the resource"),
            @ApiResponse(responseCode = "400", description = "Bad Request - The request was not valid",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class))}),
    })
    ResponseEntity<AuthResponseDTO> login(@RequestBody @Valid LoginDto loginDto);

    //sign up
    @Operation(summary = "Sign up", description = "Sign up", tags = {"auth"})
    @PostMapping("/signup")
    ResponseEntity<User> signup(@Valid @RequestBody SignUpRequestDto signUpRequestDto) throws Exception;

    @Operation(summary = "Sign up from admin", description = "Sign up", tags = {"auth"})
    @PostMapping("/registerUser")
    ResponseEntity<User> registerUser(@Valid @RequestBody RegisterUserRequestDto registerUser) throws Exception;

    @Operation(summary = "Logout", description = "Logout", tags = {"auth"})
    @PostMapping("/logout")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully logged out"),
            @ApiResponse(responseCode = "403", description = " Forbidden - The user is not authorized to access the resource"),
            @ApiResponse(responseCode = "400", description = "Bad Request - The request was not valid",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class))}),
    })
    ResponseEntity<Boolean> logout(HttpServletRequest request, HttpServletResponse response);

    @Operation(summary = "Refresh token", description = "Refresh token", tags = {"auth"})
    @PostMapping("/refreshToken")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully refreshed token"),
            @ApiResponse(responseCode = "403", description = " Forbidden - The user is not authorized to access the resource"),
            @ApiResponse(responseCode = "400", description = "Bad Request - The request was not valid",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class))}),
    })
    ResponseEntity<TokenRefreshResponseDto> refreshtoken(@RequestBody @Valid String refreshToken) throws Exception;


    @PostMapping("/tokenResetPassword")
    @Operation(summary = "Authentication from token recoveryPassword", description = "Retrieve the authentication of the user with the given token to request a change of password.", tags = {"auth"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully reset password"),
            @ApiResponse(responseCode = "403", description = " Forbidden - The user is not authorized to access the resource"),
            @ApiResponse(responseCode = "400", description = "Bad Request - The request was not valid",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class))}),
    })
    ResponseEntity<?> getAuthenticationToChangePassword(@RequestParam("token") String token);

}
