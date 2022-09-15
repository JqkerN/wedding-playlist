package com.example.weedingplaylist.guest.service;

import com.example.weedingplaylist.guest.dto.RegistrationRequest;
import com.example.weedingplaylist.guest.model.Guest;
import com.example.weedingplaylist.guest.repository.IGuestRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GuestService {
    private final IGuestRepository guestRepository;

    public List<Guest> getGuestList() {
        return guestRepository.findAll();
    }

    public void addGuest(RegistrationRequest request) {
        Guest guest = Guest.builder()
                .fullName(request.getFullName())
                .build();

        guestRepository.save(guest);
    }

    public void removeGuest(Integer id) {
        guestRepository.deleteById(id);
    }

    public Guest getGuest(Integer id) {
        return guestRepository.findById(id);
    }
}
