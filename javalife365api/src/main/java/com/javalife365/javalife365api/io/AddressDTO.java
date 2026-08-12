package com.javalife365.javalife365api.io;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.Nullable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressDTO {

    @NotBlank(message = "Street Address is required")
    private String streetAddress;

    @Nullable
    private String apartmentNumber;

    @NotBlank(message = "City is required field")
    private String city;

    @NotBlank(message = "State is required field")
    private String state;

    @NotBlank(message = "Zip Code is required field")
    @Pattern(
            regexp = "^[1-9][0-9]{4}$",
            message = "Zip Code must be 5 digits"
    )
    private String zipCode;
}
