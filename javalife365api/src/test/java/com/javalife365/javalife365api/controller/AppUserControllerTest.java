package com.javalife365.javalife365api.controller;

import com.javalife365.javalife365api.io.AddressDTO;
import com.javalife365.javalife365api.io.AppResponse;
import com.javalife365.javalife365api.io.RegisterRequest;
import com.javalife365.javalife365api.service.AppUserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AppUserControllerTest {

    @Mock
    private AppUserService appUserService;

    @InjectMocks
    private AppUserController appUserController;

    @Test
    void register_shouldReturnCreatedResponse() {

        RegisterRequest request = RegisterRequest.builder()
                .firstName("test")
                .lastName("test")
                .email("test@test.com")
                .phoneNumber("111-1111-1111")
                .password("t1234")
                .address(
                        AddressDTO.builder()
                                .streetAddress("test street")
                                .city("test city")
                                .state("test state")
                                .zipCode("11111")
                                .build()
                )

                .build();

        AppResponse appResponse = AppResponse.builder()
                .message("User saved succesfully")
                .status(HttpStatus.CREATED)
                .data(Collections.singletonList(request))
                .timestamp(LocalDateTime.now())
                .build();


        when(appUserService.register(request)).thenReturn(appResponse);

        //act
        ResponseEntity<AppResponse> finalResponse =
                appUserController.register(request);

        //assert
        assertEquals(HttpStatus.CREATED, finalResponse.getStatusCode());
        assertEquals(appResponse, finalResponse.getBody());

        verify(appUserService).register(request);


    }
}