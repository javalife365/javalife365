package com.javalife365.javalife365api.service;

import com.javalife365.javalife365api.exception.EmailNotFoundException;
import com.javalife365.javalife365api.model.AppUser;
import com.javalife365.javalife365api.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService  implements UserDetailsService {

    private final AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser appUser = appUserRepository.findByEmail(email).orElseThrow(() -> new EmailNotFoundException("Email: "+ email + " not found"));
        return  new User(
                appUser.getEmail(),
                appUser.getPassword(),
                List.of(new SimpleGrantedAuthority(appUser.getRole().name()))
        );
    }
}
