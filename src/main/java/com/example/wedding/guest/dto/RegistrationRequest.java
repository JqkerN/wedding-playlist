package com.example.wedding.guest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RegistrationRequest {
    @NotBlank(message = "Full name cannot be null or empty")
    private String fullName;
}
