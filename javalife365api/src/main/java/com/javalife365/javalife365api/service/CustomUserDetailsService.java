package com.javalife365.javalife365api.service;

import com.javalife365.javalife365api.exception.EmailNotFoundException;
import com.javalife365.javalife365api.model.AppUser;
import com.javalife365.javalife365api.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService  implements UserDetailsService {

    private final AppUserRepository appUserRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        AppUser appUser = appUserRepository.findByEmail(email).orElseThrow(() -> new EmailNotFoundException("Email: "+ email + " not found"));

        return CustomUserDetails.builder()
                .firstName(appUser.getFirstName())
                .lastName(appUser.getLastName())
                .email(appUser.getEmail())
                .password(appUser.getPassword())
                .role(appUser.getRole().name())
                .build();


    }
}
