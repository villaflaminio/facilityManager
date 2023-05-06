package it.bruffa.facilitymanager.service.impl;

import it.bruffa.facilitymanager.model.dto.AuthResponseDTO;
import it.bruffa.facilitymanager.model.dto.LoginDto;
import it.bruffa.facilitymanager.model.dto.TokenRefreshResponseDto;
import it.bruffa.facilitymanager.model.dto.request.SignUpRequestDto;
import it.bruffa.facilitymanager.model.entity.*;
import it.bruffa.facilitymanager.model.exception.ExpiredJwtException;
import it.bruffa.facilitymanager.model.exception.InvalidCredentialsException;
import it.bruffa.facilitymanager.model.exception.ItemNotFoundException;
import it.bruffa.facilitymanager.model.projection.UserMeInfo;
import it.bruffa.facilitymanager.repository.PasswordResetTokenRepository;
import it.bruffa.facilitymanager.repository.RefreshTokenRepository;
import it.bruffa.facilitymanager.repository.RoleRepository;
import it.bruffa.facilitymanager.repository.UserRepository;
import it.bruffa.facilitymanager.security.CustomAuthenticationManager;
import it.bruffa.facilitymanager.security.JwtUtility;
import it.bruffa.facilitymanager.service.AuthService;
import it.bruffa.facilitymanager.service.FeedbackService;
import it.bruffa.facilitymanager.utilities.PropertiesHelper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class FeedbackServiceImpl implements FeedbackService {


}
