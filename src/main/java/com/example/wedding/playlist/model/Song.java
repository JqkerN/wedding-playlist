package com.example.wedding.playlist.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Song {
    private Integer id;
    private String title;
    private String artist;
    private String albumCover;
    private Integer votes;

    @Override
    public String toString() {
        return "Track{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
