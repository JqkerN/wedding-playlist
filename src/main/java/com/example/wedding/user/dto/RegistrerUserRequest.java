package com.example.wedding.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RegistrerUserRequest {
    @NotBlank(message = "Has to have a username.")
    private String username;
    @NotBlank(message = "Has to have a password.")
    private String password;
}
