package com.example.wedding.playlist.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class VoteRequest {
    @NotNull(message = "Guest id cannot be null")
    private Integer guestId;
}
