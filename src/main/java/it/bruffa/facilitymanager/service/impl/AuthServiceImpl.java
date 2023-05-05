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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;
    @Autowired
    private JwtUtility tokenProvider;

    private final CustomAuthenticationManager authenticationManager;
    private final JwtUtility jwtUtility;
    private final RefreshTokenRepository refreshTokenRepository;
    private final RoleRepository roleRepository;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @Autowired
    public AuthServiceImpl(CustomAuthenticationManager authenticationManager,
                           JwtUtility jwtUtility,
                           RefreshTokenRepository refreshTokenRepository,
                           RoleRepository roleRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtUtility = jwtUtility;
        this.refreshTokenRepository = refreshTokenRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public ResponseEntity<AuthResponseDTO> login(LoginDto loginDto) {
        User user;
        UsernamePasswordAuthenticationToken authenticationToken;
        if (loginDto.getUsername() != null) {
            user = userRepository.findByUsername(loginDto.getUsername()).orElseThrow(() -> new UsernameNotFoundException("User not found!"));
            authenticationToken = new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());

        } else {
            user = userRepository.findByEmail(loginDto.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User not found!"));
            authenticationToken = new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());
        }

        try {
            authenticationManager.authenticate(authenticationToken);
        } catch (Exception e) {
            throw new InvalidCredentialsException("Invalid username or password!");
        }
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        Authentication authentication = authenticationManager.authenticate(
                authenticationToken
        );

        // Set the authentication in the Security Context.
        SecurityContextHolder.getContext().setAuthentication(authentication);


        // Generate the token and return the wrapped response.
        return ResponseEntity.ok(tokenProvider.generateAuthFromUser(user));
    }

    @Override
    public ResponseEntity<User> registerUser(SignUpRequestDto signUpRequestDto) throws Exception {
        // Check if the email is already in use.
        if (userRepository.existsByEmail(signUpRequestDto.getEmail()))
            throw new Exception("Email is already in use!");

        // Trying to find the role with the given name, if not found a bad request exception will be thrown.
        Role role = roleRepository.findByName("ROLE_USER").orElseThrow(() -> new ItemNotFoundException("Role not found"));
        // Create the collection to store all the roles.
        ArrayList<Role> roles = new ArrayList<>();
        roles.add(role);

        // Creating user's account
        User user = new User();
        PropertiesHelper.copyNonNullProperties(signUpRequestDto, user);

        user.setFirstName(signUpRequestDto.getFirstName());
        user.setLastName(signUpRequestDto.getLastName());
        user.setUsername(signUpRequestDto.getUsername());
        user.setEmail(signUpRequestDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpRequestDto.getPassword()));
        user.setRoles(roles);
        user.setEnable(true);
        user.setAccountNonLocked(true);
        user.setAccountNonExpired(true);
        user.setCredentialExpired(false);

        // Save the user in the database.
        User result = userRepository.save(user);
        result.setPassword(null);


        // Return the created user.
        return ResponseEntity.ok(result);
    }


    @Override
    public ResponseEntity<TokenRefreshResponseDto> refreshtoken(String refreshToken) throws Exception {

        // Find the refresh token in the database.
        RefreshToken refreshTokenDb = refreshTokenRepository.findByToken(refreshToken).orElseThrow(() -> new Exception("Refresh Token non trovato"));

        // Check if the refresh token is expired.
        jwtUtility.verifyExpiration(refreshTokenDb);

        // Get the user from the refresh token.
        User userLogged = refreshTokenDb.getUser();

        //Generate new Token
        String newToken = jwtUtility.generateTokenFromUser(userLogged);

        // Generate new refresh token.
        RefreshToken newRefreshToken = jwtUtility.createRefreshToken(userLogged);
        TokenRefreshResponseDto tokenRefreshResponse = new TokenRefreshResponseDto(newToken, newRefreshToken.getToken());


        // Return refresh response.
        return ResponseEntity.ok(tokenRefreshResponse);
    }

    @Override
    public ResponseEntity<?> createFirstUser(HttpServletRequest req) throws Exception {
        // Get ADMIN and USER role.
        Optional<Role> ruoloAdmin = roleRepository.findByName("ROLE_ADMIN");
        Optional<Role> ruoloUser = roleRepository.findByName("ROLE_USER");
        ArrayList<Role> roles = new ArrayList<>();

        // If the roles are not present, create them.
        if (ruoloAdmin.isEmpty()) {
            Role role = new Role();
            role.setName("ROLE_ADMIN");
            roles.add(roleRepository.save(role));
        } else {
            roles.add(ruoloAdmin.get());
        }
        if (ruoloUser.isEmpty()) {
            Role role = new Role();
            role.setName("ROLE_USER");
            roles.add(roleRepository.save(role));
        } else {
            roles.add(ruoloUser.get());
        }


        User user = new User();
        user.setEmail("admin@elis.org");
        user.setUsername("user_admin");
        user.setFirstName("firstName_admin");
        user.setLastName("lastName_admin");
        user.setPassword(passwordEncoder.encode("password"));
        user.setRoles(roles);
        user.setAccountNonLocked(true);
        user.setAccountNonExpired(true);
        user.setCredentialExpired(false);
        user.setEnable(true);



        // Save the user in the database.
        try {
            userRepository.save(user);
        } catch (Exception e) {
            throw new InvalidCredentialsException("User already present!");

        }
        return ResponseEntity.ok(user);
    }

    @Override
    public ResponseEntity<UserMeInfo> getCurrentUser(UserPrincipal userPrincipal) {


        UserMeInfo user = userRepository.getUserProjectedMeById(userPrincipal.getId())
                .orElseThrow(() -> new UsernameNotFoundException("User not found with id : " + userPrincipal.getId()));

        return ResponseEntity.ok(user);
    }

    @Override
    public ResponseEntity<?> getAuthenticationToChangePassword(String token) {
        // Find the password reset token using the given token.
        Optional<PasswordResetToken> userPasswToken = passwordResetTokenRepository.findByToken(token);

        // Check if the token is present.
        if (!userPasswToken.isPresent()) {
            throw new ExpiredJwtException("Token non valido");
        }

        // Retrieve the user from the token.
        User user = userPasswToken.get().getUser();

        // Request the token to change the password.

        String result = validatePasswordResetToken(token);
        if (result != null) {
            throw new ExpiredJwtException("Token non valido");
        } else {
            return ResponseEntity.ok(tokenProvider.generateAuthFromUser(user));
        }
    }

    @Override
    public ResponseEntity<Boolean> logout(HttpServletRequest request, HttpServletResponse response) {
        // Get the Spring Authentication object of the current request.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // In case you are not filtering the users of this request url.
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication); // <= This is the call you are looking for.
        }
        return ResponseEntity.ok(true);
    }

    /**
     * Check if the token is found.
     *
     * @param passToken The token to check.
     * @return True if the token is found.
     */
    private boolean isTokenFound(PasswordResetToken passToken) {
        return passToken != null;
    }
    /**
     * Validate the password reset token.
     * @param token The token to validate.
     * @return invalidToken if the token is invalid, expired if the token is expired
     */

    /**
     * Check if the token is expired.
     *
     * @param passToken The token to check.
     * @return True if the token is expired.
     */
    private boolean isTokenExpired(PasswordResetToken passToken) {
        return passToken.getExpiryDate().compareTo(Instant.now()) <= 0;
    }

    public String validatePasswordResetToken(String token) {
        Optional<PasswordResetToken> userPasswToken = passwordResetTokenRepository.findByToken(token);
        if (!userPasswToken.isPresent()) {
            throw new ExpiredJwtException("Token non valido");
        }
        final PasswordResetToken passToken = userPasswToken.get();

        return !isTokenFound(passToken) ? "invalidToken"
                : isTokenExpired(passToken) ? "expired"
                : null;
    }

    public ResponseEntity<?> session(User user) {
        return ResponseEntity.ok(tokenProvider.generateAuthFromUser(user));

    }

}
