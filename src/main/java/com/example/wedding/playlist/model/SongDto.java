package com.example.wedding.playlist.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SongDto {
    private Integer id;
    private String title;
    private String artist;
    private String albumCover;

    @Override
    public String toString() {
        return "PlaylistDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                ", albumCover='" + albumCover + '\'' +
                '}';
    }
}
