package com.example.weedingplaylist.vote.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class VoteCount {
    private String title;
    private Integer count;
}
