package com.javalife365.javalife365api.service;

import com.javalife365.javalife365api.email.EmailService;
import com.javalife365.javalife365api.exception.EmailAlreadyExistsException;
import com.javalife365.javalife365api.exception.PhoneNumberAlreadyExistsException;
import com.javalife365.javalife365api.io.AppResponse;
import com.javalife365.javalife365api.io.RegisterRequest;
import com.javalife365.javalife365api.mapper.AppUserMapper;
import com.javalife365.javalife365api.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppUserService {


    private final AppUserRepository appUserRepository;
    private final AppUserMapper appUserMapper;
    private final EmailService emailService;

    public AppResponse register(RegisterRequest request){
        log.info("request: {}", request);

        if (appUserRepository.existsByEmail(request.email())){
            throw new EmailAlreadyExistsException("Email: "+ request.email() + " already exists" );
        }
        if(appUserRepository.existsByPhoneNumber(request.phoneNumber())){
            throw new PhoneNumberAlreadyExistsException("Phone number: " + request.phoneNumber() + " already exists");
        }

        var appUser = appUserMapper.toAppUser(request);

        var savedUser = appUserRepository.save(appUser);

        var appUserDTO = appUserMapper.toAppUserDTO(savedUser);

        emailService.sendEmailAfterRegistration(appUserDTO.getEmail());

        return AppResponse.builder()
                .message("User saved successfully")
                .status(HttpStatus.CREATED)
                .data(List.of(appUserDTO))
                .timestamp(LocalDateTime.now())
                .build();
    }




}
