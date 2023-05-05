package it.bruffa.facilitymanager.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;

public class CustomAuthenticationManager implements AuthenticationManager {

    @Override
    public Authentication authenticate(Authentication authentication) {
        return authentication;
    }

}
