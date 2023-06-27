package it.flaminiovilla.facilitymanager.service.impl;

import it.flaminiovilla.facilitymanager.model.dto.AuthResponseDTO;
import it.flaminiovilla.facilitymanager.model.dto.LoginDto;
import it.flaminiovilla.facilitymanager.model.dto.TokenRefreshResponseDto;
import it.flaminiovilla.facilitymanager.model.dto.request.RegisterUserRequestDto;
import it.flaminiovilla.facilitymanager.model.dto.request.SignUpRequestDto;
import it.flaminiovilla.facilitymanager.model.entity.*;
import it.flaminiovilla.facilitymanager.model.exception.ExpiredJwtException;
import it.flaminiovilla.facilitymanager.model.exception.InvalidCredentialsException;
import it.flaminiovilla.facilitymanager.model.exception.ItemNotFoundException;
import it.flaminiovilla.facilitymanager.model.projection.UserMeInfo;
import it.flaminiovilla.facilitymanager.repository.PasswordResetTokenRepository;
import it.flaminiovilla.facilitymanager.repository.RefreshTokenRepository;
import it.flaminiovilla.facilitymanager.repository.RoleRepository;
import it.flaminiovilla.facilitymanager.repository.UserRepository;
import it.flaminiovilla.facilitymanager.security.CustomAuthenticationManager;
import it.flaminiovilla.facilitymanager.security.JwtUtility;
import it.flaminiovilla.facilitymanager.service.AuthService;
import it.flaminiovilla.facilitymanager.service.UserService;
import it.flaminiovilla.facilitymanager.utilities.PropertiesHelper;
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
public class AuthServiceImpl implements AuthService {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;
    @Autowired
    private JwtUtility tokenProvider;
    @Autowired
    private UserService userService;

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

    /**
     * @param signUpRequestDto
     * @return
     */
    @Override
    public ResponseEntity<AuthResponseDTO> login(LoginDto loginDto) {
        User user;
        UsernamePasswordAuthenticationToken authenticationToken;
        // Check if the user is trying to login with username or email.
        if (loginDto.getUsername() != null) {
            user = userRepository.findByUsername(loginDto.getUsername()).orElseThrow(() -> new UsernameNotFoundException("User not found!"));
            authenticationToken = new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());

        } else {
            user = userRepository.findByEmail(loginDto.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User not found!"));
            authenticationToken = new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());
        }
        if(!user.getEnable()){
            throw new InvalidCredentialsException("User is not enabled!");
        }
        try {
            // Try to authenticate the user.

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

    /**
     * @param signUpRequestDto
     * @return
     * @throws Exception
     */
    @Override
    public ResponseEntity<User> signup(SignUpRequestDto signUpRequestDto) throws Exception {
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

        // Set the user's data.
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

    /**
     * @param email
     * @return
     * @throws Exception
     */

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

    /**
     * @param email
     * @return
     * @throws Exception
     */
    @Override
    public ResponseEntity<?> createFirstUser(HttpServletRequest req) throws Exception {
        // Get ADMIN and USER role.
        Optional<Role> ruoloAdmin = roleRepository.findByName("ROLE_ADMIN");
        Optional<Role> ruoloUser = roleRepository.findByName("ROLE_USER");
        Optional<Role> ruoloMaintainer = roleRepository.findByName("ROLE_MAINTAINER");
        Optional<Role> ruoloCleaner = roleRepository.findByName("ROLE_CLEANER");
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
        if (ruoloMaintainer.isEmpty()) {
            Role role = new Role();
            role.setName("ROLE_MAINTAINER");
            roles.add(roleRepository.save(role));
        } else {
            roles.add(ruoloMaintainer.get());
        }
        if (ruoloCleaner.isEmpty()) {
            Role role = new Role();
            role.setName("ROLE_CLEANER");
            roles.add(roleRepository.save(role));
        } else {
            roles.add(ruoloCleaner.get());
        }
        User user = new User();
        user.setEmail("bruffa@gmail.com");
        user.setUsername("user_admin");
        user.setFirstName("firstName_admin");
        user.setLastName("lastName_admin");
        user.setPassword(passwordEncoder.encode("password"));
        user.setRoles(roles);
        user.setAccountNonLocked(true);
        user.setAccountNonExpired(true);
        user.setCredentialExpired(false);
        user.setLatitude(0.0);
        user.setLongitude(0.0);
        user.setEnable(true);

        try {
            userRepository.save(user);
        } catch (Exception e) {
            throw e;

        }
        return ResponseEntity.ok(user);
    }

    /**
     * @param email
     * @return
     * @throws Exception
     */
    @Override
    public ResponseEntity<UserMeInfo> getCurrentUser(UserPrincipal userPrincipal) {


        UserMeInfo user = userRepository.getUserProjectedMeById(userPrincipal.getId())
                .orElseThrow(() -> new UsernameNotFoundException("User not found with id : " + userPrincipal.getId()));

        return ResponseEntity.ok(user);
    }

    /**
     * @param email
     * @return
     * @throws Exception
     */
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

    /**
     * @return
     * @throws Exception
     */
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

    @Override
    public ResponseEntity<User> registerUser(RegisterUserRequestDto registerUser) throws Exception {
        // Check if the email is already in use.
        if (userRepository.existsByEmail(registerUser.getEmail()))
            throw new Exception("Email is already in use!");

        // Trying to find the role with the given name, if not found a bad request exception will be thrown.
        Role role = roleRepository.findByName(registerUser.getRole()).orElseThrow(() -> new ItemNotFoundException("Role not found"));
        // Create the collection to store all the roles.
        ArrayList<Role> roles = new ArrayList<>();
        roles.add(role);

        // Creating user's account
        User user = new User();

        user.setFirstName(registerUser.getFirstName());
        user.setLastName(registerUser.getLastName());
        user.setEmail(registerUser.getEmail());
        user.setLatitude(registerUser.getLatitude());
        user.setLongitude(registerUser.getLongitude());
        user.setRoles(roles);
        user.setEnable(true);
        user.setAccountNonLocked(true);
        user.setAccountNonExpired(true);
        user.setCredentialExpired(false);

        // Save the user in the database.
        User result = userRepository.save(user);

        userService.requestResetPassword(result);
        // Return the created user.
        return ResponseEntity.ok(result);

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
