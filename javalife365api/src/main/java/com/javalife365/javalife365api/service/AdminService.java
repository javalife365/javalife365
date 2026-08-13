package com.javalife365.javalife365api.service;

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
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminService {

    private final AppUserRepository appUserRepository;
    private final AppUserMapper appUserMapper;
    private final WebRequest webRequest;

    public AppResponse register(RegisterRequest request){
        log.info("admin register request: {}",request);

        if (appUserRepository.existsByEmail(request.email())){
            throw new EmailAlreadyExistsException("Email: "+ request.email() + " already exists" );
        }
        log.info("checked if email {} already existed in db: {}", request.email(), false);
        if(appUserRepository.existsByPhoneNumber(request.phoneNumber())){
            throw new PhoneNumberAlreadyExistsException("Phone number: " + request.phoneNumber() + " already exists");
        }
        log.info("checked if phone number {} already existed in db: {}", request.phoneNumber(), false);

        var  admin = appUserMapper.toAdmin(request);

        admin = appUserRepository.save(admin);

        return AppResponse.builder()
                .message("Registered successfully")
                .status(HttpStatus.CREATED)
                .data(List.of(appUserMapper.toAppUserDTO(admin)))
                .url(webRequest.getDescription(true))
                .timestamp(LocalDateTime.now())
                .build();




    }
}
