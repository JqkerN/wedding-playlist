package com.example.wedding.playlist.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Vote {
    private Integer guestId;
    private Integer songId;
}
