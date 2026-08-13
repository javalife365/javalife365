package com.javalife365.javalife365api.service;

import com.javalife365.javalife365api.io.LoginRequest;
import com.javalife365.javalife365api.io.LoginResponse;
import com.javalife365.javalife365api.repository.AppUserRepository;
import com.javalife365.javalife365api.security.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final AppUserRepository appUserRepository;

    public LoginResponse login(LoginRequest request){
        log.info("login request: {}",request);
        Authentication authentication
                = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));

        log.info("post authentication success");

        var customUserDetails = (CustomUserDetails) authentication.getPrincipal();

        String token =  jwtService.generateToken(request.getEmail(), customUserDetails.getFirstName(),customUserDetails.getLastName(), customUserDetails.getRole());
        String name = jwtService.extractName(token);
        String role = jwtService.extractRole(token);
        Date issuedAt = jwtService.extractIssuedAt(token);
        Date expiresAt = jwtService.extractExpiresAt(token);

        log.info("jwt token generated and login response returned");

        return LoginResponse.builder()
                .token(token)
                .name(name)
                .role(role)
                .issuedAt(issuedAt)
                .expiresAt(expiresAt)
                .build();
    }
}
