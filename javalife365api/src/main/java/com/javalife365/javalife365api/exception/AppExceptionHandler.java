package com.javalife365.javalife365api.exception;

import com.javalife365.javalife365api.io.AppResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class AppExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<AppResponse> handlePhoneNumberNotExistsException(PhoneNumberNotExistsException ex, WebRequest webRequest) {
        log.info(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(
                        AppResponse.builder()
                                .message(ex.getMessage())
                                .status(HttpStatus.NOT_FOUND)
                                .url(webRequest.getDescription(true))
                                .timestamp(LocalDateTime.now())
                                .build()
                );
    }

    @ExceptionHandler
    public ResponseEntity<AppResponse> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex, WebRequest webRequest) {
        log.info(ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(
                        AppResponse.builder()
                                .message(ex.getMessage())
                                .status(HttpStatus.CONFLICT)
                                .url(webRequest.getDescription(true))
                                .timestamp(LocalDateTime.now())
                                .build()
                );
    }

    @ExceptionHandler
    public ResponseEntity<AppResponse> handlePhoneNumberAlreadyExistsException(PhoneNumberAlreadyExistsException ex, WebRequest webRequest) {
        log.info(ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(
                        AppResponse.builder()
                                .message(ex.getMessage())
                                .status(HttpStatus.CONFLICT)
                                .url(webRequest.getDescription(true))
                                .timestamp(LocalDateTime.now())
                                .build()

                );
    }

    @ExceptionHandler
    public ResponseEntity<AppResponse> handleEmailDeliveryFailedException(EmailDeliveryFailedException ex, WebRequest webRequest) {
        log.info(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(
                        AppResponse.builder()
                                .message(ex.getMessage())
                                .status(HttpStatus.BAD_REQUEST)
                                .url(webRequest.getDescription(true))
                                .build()
                );
    }


}
