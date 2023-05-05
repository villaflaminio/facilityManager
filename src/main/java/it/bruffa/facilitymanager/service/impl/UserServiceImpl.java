package it.bruffa.facilitymanager.service.impl;

import it.bruffa.facilitymanager.constants.SecurityConstants;
import it.bruffa.facilitymanager.model.dto.MailResponse;
import it.bruffa.facilitymanager.model.dto.request.ChangePassworRequest;
import it.bruffa.facilitymanager.model.entity.PasswordResetToken;
import it.bruffa.facilitymanager.model.entity.User;
import it.bruffa.facilitymanager.model.entity.UserPrincipal;
import it.bruffa.facilitymanager.model.exception.ItemNotFoundException;
import it.bruffa.facilitymanager.model.projection.UserDetailInfo;
import it.bruffa.facilitymanager.repository.PasswordResetTokenRepository;
import it.bruffa.facilitymanager.repository.UserRepository;
import it.bruffa.facilitymanager.service.UserService;
import it.bruffa.facilitymanager.utilities.EmailService;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
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
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

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
}
