package com.javalife365.javalife365api.mapper;

import com.javalife365.javalife365api.io.AddressDTO;
import com.javalife365.javalife365api.io.AppUserDTO;
import com.javalife365.javalife365api.io.RegisterRequest;
import com.javalife365.javalife365api.model.Address;
import com.javalife365.javalife365api.model.AppUser;
import com.javalife365.javalife365api.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class AppUserMapper {

    private final PasswordEncoder passwordEncoder;

    public AppUser toAppUser(RegisterRequest request){

        var address = Address.builder()
                .streetAddress(request.address().getStreetAddress())
                .city(request.address().getCity())
                .state(request.address().getState())
                .zipCode(request.address().getZipCode())
                .build();

        return AppUser.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .phoneNumber(request.phoneNumber())
                .password(passwordEncoder.encode(request.password()))
                .role(Role.ROLE_USER)
                .address(address)
                .createdAt(LocalDateTime.now())
                .build();
    }


    public AppUserDTO toAppUserDTO(AppUser appUser){
        return AppUserDTO.builder()
                .firstName(appUser.getFirstName())
                .lastName(appUser.getLastName())
                .email(appUser.getEmail())
                .phoneNumber(appUser.getPhoneNumber())
                .address(
                        AddressDTO.builder()
                                .streetAddress(appUser.getAddress().getStreetAddress())
                                .apartmentNumber(appUser.getAddress().getApartmentNumber())
                                .city(appUser.getAddress().getCity())
                                .state(appUser.getAddress().getState())
                                .zipCode(appUser.getAddress().getZipCode())
                                .build()
                )
                .build();
    }
}
