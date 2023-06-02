package com.project.contactmanagement.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    public static final String[] SECURED_ADMIN = {
            "/contact/v1/create",
            "/contact/v1/delete/**"
    };

    public static final String[] SECURED_USERS = {
            "/contact/v1/id/**",
            "/contact/v1/firstName/**",
            "/contact/v1/lastName/**"
    };
    public static final String[] UNSECURED = {
            "/contact/v1/all",
            "/h2-console/**"
    };

    @Bean
    public UserDetailsService userDetailsService(){
        return new UserDetailsServiceImpl();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.headers().frameOptions().disable();
        return httpSecurity.csrf().disable().authorizeHttpRequests()
                .requestMatchers(UNSECURED).permitAll()
                .and().authorizeHttpRequests()
                .requestMatchers(SECURED_ADMIN).hasAuthority("ADMIN").and()
                .authorizeHttpRequests().requestMatchers(SECURED_USERS).hasAuthority("USER")
                .anyRequest().authenticated().and().httpBasic().and().build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
}
