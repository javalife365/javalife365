package com.javalife365.javalife365api.io;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppResponse {
    private String message;
    private HttpStatus status;
    private List<Object> data;
    private LocalDateTime timestamp;
    private String url;
}
