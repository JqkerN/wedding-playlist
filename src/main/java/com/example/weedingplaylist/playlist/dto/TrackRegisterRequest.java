package com.example.weedingplaylist.playlist.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TrackRegisterRequest {
    @NotBlank
    private String title;
}
