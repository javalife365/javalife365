package com.javalife365.javalife365api.io;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;

@Builder
public record RegisterRequest(

        @NotBlank(message = "First Name is required")
        String firstName,

        @NotBlank(message = "Last Name is required")
        String lastName,

        @NotBlank(message = "Email is required")
        @Email(message = "Invalid Email format")
//        @Pattern(
//                regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
//                message = "Email is invalid format"
//        )
        String email,

        @NotBlank(message = "Phone Number is required")
        @Pattern(
                regexp = "^[0-9]{3}-[0-9]{3}-[0-9]{4}$",
                message = "Invalid Phone Number. Must be in XXX-XXX-XXXX format"
        )
        String phoneNumber,

        @NotBlank(message = "Password is required")
        String password,

        @NotNull(message = "Address is required")
        @Valid
        AddressDTO address

) { }



