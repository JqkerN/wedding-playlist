package com.example.weedingplaylist.vote.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class VoteRequest {
    @NotBlank
    private String fullName;
    @NotBlank
    private String title;
}
