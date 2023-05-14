package it.bruffa.facilitymanager.service.impl;

import it.bruffa.facilitymanager.constants.SecurityConstants;
import it.bruffa.facilitymanager.model.dto.MailResponse;
import it.bruffa.facilitymanager.model.dto.request.ChangePassworRequest;
import it.bruffa.facilitymanager.model.entity.PasswordResetToken;
import it.bruffa.facilitymanager.model.entity.Role;
import it.bruffa.facilitymanager.model.entity.User;
import it.bruffa.facilitymanager.model.entity.UserPrincipal;
import it.bruffa.facilitymanager.model.exception.ItemNotFoundException;
import it.bruffa.facilitymanager.model.projection.UserDetailInfo;
import it.bruffa.facilitymanager.repository.PasswordResetTokenRepository;
import it.bruffa.facilitymanager.repository.RoleRepository;
import it.bruffa.facilitymanager.repository.UserRepository;
import it.bruffa.facilitymanager.service.UserService;
import it.bruffa.facilitymanager.utilities.EmailService;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Instant;
import java.util.*;

import static it.bruffa.facilitymanager.constants.ExceptionMessages.USER_NOT_FOUND;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Autowired
    private EmailService emailService;
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public User changePassword(UserPrincipal userPrincipal, ChangePassworRequest body) {
        User user = userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ItemNotFoundException("User not found!"));

        // Update the password.
        user.setPassword(body.getNewPassword());

        // Encode the new password.
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Save the user.
        User updatedUser = userRepository.save(user);
        updatedUser.setPassword(null);
        return updatedUser;
    }

    @Override
    public Object recoveryPassword(String userEmail) {
        // Find the user with the given email.
        User user = userRepository.findByEmail(userEmail).orElseThrow(() -> new ItemNotFoundException(USER_NOT_FOUND));

        // Return the response (200 - OK) and call the method to send the email to recover password.
        return ResponseEntity.ok(requestResetPassword(user));
    }

    @Override
    public UserDetailInfo getUserById(Long userId) {

        return userRepository.getUserById(userId).orElseThrow(() -> new ItemNotFoundException(USER_NOT_FOUND));
    }


    /**
     * Request a password reset.
     *
     * @param user The user that is requesting the reset.
     * @return The mail response.
     */
    public MailResponse requestResetPassword(User user) {
        // Create a new token to reset the password.
        String token = UUID.randomUUID().toString();
        PasswordResetToken passwordResetToken = new PasswordResetToken();
        passwordResetToken.setToken(token);
        passwordResetToken.setUser(user);
        passwordResetToken.setExpiryDate(Instant.now().plusSeconds((SecurityConstants.REFRESH_TOKEN_EXPIRATION)));

        // Save the password reset token
        passwordResetTokenRepository.save(passwordResetToken);

        // Send the email
        Map<String, Object> model = new HashMap<>();
        model.put("name", user.getFirstName());
        model.put("indirizzo", SecurityConstants.FE_URL + "/recupera-password/" + token);
        return emailService.sendEmail(user.getEmail(), "Reset password", model, "recuperoPassword");
    }

    @Override
    public ResponseEntity<Boolean> changeRole(Long userId, String role) {
        try {
            logger.debug("Change role for user with id: {}", userId);
            User user = userRepository.findById(userId).orElseThrow(() -> new ItemNotFoundException(USER_NOT_FOUND));
            List<Role> roles = roleRepository.findAll();
            Role admin = roles.stream().filter(r -> r.getName().equals("ROLE_ADMIN")).findFirst().get();
            Role userRole = roles.stream().filter(r -> r.getName().equals("ROLE_USER")).findFirst().get();
            Role cleaner = roles.stream().filter(r -> r.getName().equals("ROLE_CLEANER")).findFirst().get();
            Role maintainer = roles.stream().filter(r -> r.getName().equals("ROLE_MAINTAINER")).findFirst().get();
            List<Role> selectedRoles = new ArrayList<>();

            switch (role) {

                case "ROLE_ADMIN" -> {
                    selectedRoles.add(admin);
                    selectedRoles.add(userRole);
                }
                case "ROLE_MAINTAINER" -> {
                    selectedRoles.add(maintainer);
                }
                case "ROLE_CLEANER" -> {
                    selectedRoles.add(cleaner);
                }
                case "ROLE_USER" -> selectedRoles.add(userRole);
            }

            user.setRoles(selectedRoles);
            userRepository.save(user);
            return ResponseEntity.ok(true);
        } catch (ItemNotFoundException e) {
            logger.error("User not found!");
            throw new RuntimeException(e);
        }
    }
}
