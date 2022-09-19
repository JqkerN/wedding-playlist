package com.example.wedding.security.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Builder
@Getter
public class RegistrerUserRequest {
    @NotBlank(message = "Has to have a username.")
    private String username;
    @NotBlank(message = "Has to have a password.")
    private String password;
}
