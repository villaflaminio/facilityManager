package it.bruffa.facilitymanager.controller.impl;

import it.bruffa.facilitymanager.controller.AuthController;
import it.bruffa.facilitymanager.model.dto.AuthResponseDTO;
import it.bruffa.facilitymanager.model.dto.LoginDto;
import it.bruffa.facilitymanager.model.dto.TokenRefreshResponseDto;
import it.bruffa.facilitymanager.model.dto.request.RegisterUserRequestDto;
import it.bruffa.facilitymanager.model.dto.request.SignUpRequestDto;
import it.bruffa.facilitymanager.model.entity.User;
import it.bruffa.facilitymanager.model.entity.UserPrincipal;
import it.bruffa.facilitymanager.model.projection.UserMeInfo;
import it.bruffa.facilitymanager.security.CurrentUser;
import it.bruffa.facilitymanager.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static it.bruffa.facilitymanager.constants.Endpoints.API;
import static it.bruffa.facilitymanager.constants.Endpoints.AUTH;


@RestController
@RequestMapping(API + AUTH)
public class AuthControllerImpl implements AuthController {

    @Autowired
    private AuthService authService;

    /**
     * Login.
     *
     * @param loginDto the login dto
     * @return the response entity
     */
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody @Valid LoginDto loginDto) {
        try {
            return authService.login(loginDto);
        } catch (Exception e) {
            throw e;
        }
    }
    /**
     * Get the current user.
     *
     * @param userPrincipal the current user
     * @return the current user
     */
    @GetMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UserMeInfo> getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        // Return the current user found by id.
        return authService.getCurrentUser(userPrincipal);
    }

    /**
     * Signup.
     *
     * @param signUpRequestDto the sign up request dto
     * @return the response entity
     * @throws Exception the exception
     */
    @Override
    public ResponseEntity<User> signup(SignUpRequestDto signUpRequestDto) throws Exception {
        return authService.signup(signUpRequestDto);
    }

    /**
     * Register user.
     *
     * @param registerUser the register user
     * @return the response entity
     * @throws Exception the exception
     */
    @Override
    public ResponseEntity<User> registerUser(RegisterUserRequestDto registerUser) throws Exception {
        return  authService.registerUser(registerUser);
    }

    /**
     * Logout.
     *
     * @param request  the request
     * @param response the response
     * @return the response entity
     */
    @Override
    public ResponseEntity<Boolean> logout(HttpServletRequest request, HttpServletResponse response) {
        return  authService.logout(request, response);
    }

/**
     * Refresh token.
     *
     * @param refreshToken the refresh token
     * @return the response entity
     * @throws Exception the exception
     */
    @PostMapping("/refreshToken")
    public ResponseEntity<TokenRefreshResponseDto> refreshtoken(@RequestBody @Valid String refreshToken) throws Exception {
        return authService.refreshtoken(refreshToken);
    }
    /**
     * Get authentication to change password.
     *
     * @param token the token
     * @return the authentication to change password
     */

    @Override
    public ResponseEntity<?> getAuthenticationToChangePassword(String token) {
        return  authService.getAuthenticationToChangePassword(token);
    }

    /**
     * Create the first user in the database.
     *
     * @return the created user
     */
    @PostMapping("/createFirstUser")
    public ResponseEntity<?> createFirstUser(HttpServletRequest req) throws Exception {
        return authService.createFirstUser(req);
    }



}


