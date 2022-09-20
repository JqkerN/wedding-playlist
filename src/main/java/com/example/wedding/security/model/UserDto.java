package com.example.wedding.security.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserDto {
    private Integer id;
    private String username;
    private String password;
    private Role role;
}
