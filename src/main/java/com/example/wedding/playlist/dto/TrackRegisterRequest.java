package com.example.wedding.playlist.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TrackRegisterRequest {
    @NotBlank(message = "Title cannot be null or empty")
    private String title;
}
