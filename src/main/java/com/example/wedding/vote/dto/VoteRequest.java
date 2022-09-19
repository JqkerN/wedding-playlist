package com.example.wedding.vote.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class VoteRequest {
    @NotBlank(message = "Full name cannot be null or empty")
    private String fullName;
    @NotBlank(message = "Title cannot be null or empty")
    private String title;
}
