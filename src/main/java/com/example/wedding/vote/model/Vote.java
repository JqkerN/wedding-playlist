package com.example.wedding.vote.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Vote {
    private Integer guestId;
    private Integer trackId;
}
