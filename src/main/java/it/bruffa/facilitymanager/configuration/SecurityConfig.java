package it.bruffa.facilitymanager.configuration;


import it.bruffa.facilitymanager.security.CustomAuthenticationManager;
import it.bruffa.facilitymanager.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception
    { http
            .csrf()
                .disable()
            .authorizeHttpRequests()
            .requestMatchers("/api/auth/**")
            .permitAll()
            .requestMatchers("/**")
            .permitAll()
//            .anyRequest()
//                .authenticated()
            .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

//            .requestMatchers("/v3/api-docs/**").permitAll()
//            .requestMatchers("/configuration/**").permitAll()
//            .requestMatchers("/swagger/**").permitAll()
//            .requestMatchers("/webjars/**").permitAll()
//            .requestMatchers("/swagger-ui/**").permitAll()
//            .requestMatchers("/api/authentication/**").permitAll()
        http.headers().frameOptions().disable();

        return  http.build();
    }

    @Bean
    public CustomAuthenticationManager authenticationManager()
    { return new CustomAuthenticationManager();}

    @Bean
    public PasswordEncoder passwordEncoder()
    { return new BCryptPasswordEncoder(); }

}
