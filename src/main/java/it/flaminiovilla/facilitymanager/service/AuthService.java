package it.flaminiovilla.facilitymanager.service;

import it.flaminiovilla.facilitymanager.model.dto.AuthResponseDTO;
import it.flaminiovilla.facilitymanager.model.dto.LoginDto;
import it.flaminiovilla.facilitymanager.model.dto.TokenRefreshResponseDto;
import it.flaminiovilla.facilitymanager.model.dto.request.RegisterUserRequestDto;
import it.flaminiovilla.facilitymanager.model.dto.request.SignUpRequestDto;
import it.flaminiovilla.facilitymanager.model.entity.User;
import it.flaminiovilla.facilitymanager.model.entity.UserPrincipal;
import it.flaminiovilla.facilitymanager.model.projection.UserMeInfo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<AuthResponseDTO> login(LoginDto loginDto);

    ResponseEntity<User> signup(SignUpRequestDto signUpRequestDto) throws Exception;


    ResponseEntity<TokenRefreshResponseDto> refreshtoken(String refreshToken) throws Exception;

    ResponseEntity<?> createFirstUser(HttpServletRequest req) throws Exception;

    ResponseEntity<UserMeInfo> getCurrentUser(UserPrincipal userPrincipal);

    ResponseEntity<?> getAuthenticationToChangePassword(String token);

    ResponseEntity<Boolean> logout(HttpServletRequest request, HttpServletResponse response);

    ResponseEntity<User> registerUser(RegisterUserRequestDto registerUser) throws Exception;
}
