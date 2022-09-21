package com.example.wedding.playlist.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class VoteDto {
    private Integer songId;
    private Integer count;
    private String title;
    private String artist;
    private String albumCover;

    @Override
    public String toString() {
        return "VoteDto{" +
                "songId=" + songId +
                ", count=" + count +
                ", title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                ", albumCover='" + albumCover + '\'' +
                '}';
    }
}
