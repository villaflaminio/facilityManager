package it.bruffa.facilitymanager.service.impl;


import it.bruffa.facilitymanager.model.entity.User;
import it.bruffa.facilitymanager.model.entity.UserPrincipal;
import it.bruffa.facilitymanager.model.exception.ItemNotFoundException;
import it.bruffa.facilitymanager.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.*;


/**
 * Service to handle user details.
 */
@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    private Environment env;

    /**
     * Load user by email.
     * @param email The username.
     * @return The user details.
     * @throws UsernameNotFoundException
     */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with email : " + email)
        );

        return UserPrincipal.create(user);
    }



    /**
     * Load user by id.
     * @param id The id of the user.
     * @return The UserDetails.
     */
    @Transactional
    public UserDetails loadUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
            () -> new ItemNotFoundException("user")
        );

        return UserPrincipal.create(user);
    }
}