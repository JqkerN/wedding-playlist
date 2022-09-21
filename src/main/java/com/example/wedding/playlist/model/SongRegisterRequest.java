package com.example.wedding.playlist.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SongRegisterRequest {
    @NotNull(message = "Guest id cannot be null")
    private Integer guestId;
    @NotBlank(message = "Title cannot be null or empty")
    private String title;
    @NotNull(message = "Artist cannot be null")
    private String artist;
    @NotNull(message = "Album cover cannot be null")
    private String albumCover;
}
