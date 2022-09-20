package com.example.wedding.security.model;

public enum Role {
    USER, ADMIN;

    public static Role fromString(String role) {
        if ("ADMIN".equalsIgnoreCase(role)) return ADMIN;
        if ("USER".equalsIgnoreCase(role)) return USER;
        throw new RuntimeException("Invalid role["+role+"] type, has to be ADMIN or USER.");
    }
}
