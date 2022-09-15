package com.example.weedingplaylist.guest.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Guest {
    private Integer id;
    private String fullName;

    @Override
    public String toString() {
        return "Guest{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}
