package com.example.weedingplaylist.playlist.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Track {
    private Integer id;
    private String title;

    @Override
    public String toString() {
        return "Track{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
