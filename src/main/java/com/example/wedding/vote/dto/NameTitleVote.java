package com.example.wedding.vote.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class NameTitleVote {
    private Integer id;
    private String fullName;
    private String title;
}
