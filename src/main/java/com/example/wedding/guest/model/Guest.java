package com.example.wedding.guest.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Guest {
    private Integer id;
    private Integer userId;
    private String fullName;

    @Override
    public String toString() {
        return "Guest{" +
                "id=" + id +
                ", userId=" + userId +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}
