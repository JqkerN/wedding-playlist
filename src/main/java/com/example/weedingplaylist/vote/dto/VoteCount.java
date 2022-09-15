package com.example.weedingplaylist.vote.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class VoteCount {
    private String title;
    private Integer count;
}