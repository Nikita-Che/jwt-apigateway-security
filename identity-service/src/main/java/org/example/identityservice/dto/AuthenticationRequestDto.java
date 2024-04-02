package org.example.identityservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import org.example.identityservice.util.ValidationConstants;

import java.io.Serializable;

public record AuthenticationRequestDto(
//        @NotBlank(message = "Email is mandatory!")
//        @Pattern(regexp = ValidationConstants.REGEXP_VALIDATE_EMAIL, message = "Invalid e-mail address")
//        @Schema(defaultValue = "n@n.ru", description = "Email address")
        String email,

//        @NotBlank(message = "Password is mandatory!")
//        @Schema(defaultValue = "root", description = "Password")
        String password) implements Serializable {
}