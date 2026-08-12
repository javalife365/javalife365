package com.javalife365.javalife365api.service;

import com.javalife365.javalife365api.exception.PhoneNumberNotExistsException;
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
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        AppUser appUser = appUserRepository.findByPhoneNumber(phoneNumber).orElseThrow(() -> new PhoneNumberNotExistsException("Phone Number: "+ phoneNumber + " not found"));
        return  new User(
                appUser.getPhoneNumber(),
                "",
                List.of(new SimpleGrantedAuthority(appUser.getRole().name()))
        );
    }
}
