package com.example.weedingplaylist.guest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RegistrationRequest {
    @NotBlank
    private String fullName;
}
