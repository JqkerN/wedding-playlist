package com.example.weedingplaylist.guest.repository;

import com.example.weedingplaylist.guest.model.Guest;

import java.util.List;

public interface IGuestRepository {
    int save(Guest guest);

    int deleteById(Integer id);

    List<Guest> findAll();

    Guest findById(Integer id);

    Guest findByFullName(String fullName);
}
