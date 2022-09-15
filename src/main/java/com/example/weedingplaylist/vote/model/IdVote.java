package com.example.weedingplaylist.vote.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class IdVote {
    private Integer guestId;
    private Integer trackId;
}
