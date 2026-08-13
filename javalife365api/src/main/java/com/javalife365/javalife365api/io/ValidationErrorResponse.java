package com.javalife365.javalife365api.io;

import lombok.Builder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

@Builder
public record ValidationErrorResponse(
        String message,
        HttpStatus status,
        Map<String, String> errors,
        String url,
        LocalDateTime  timestamp
)
{}
