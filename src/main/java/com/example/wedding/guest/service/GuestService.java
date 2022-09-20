package com.example.wedding.guest.service;

import com.example.wedding.exception.UserNotFoundException;
import com.example.wedding.guest.dto.RegistrationRequest;
import com.example.wedding.guest.model.Guest;
import com.example.wedding.guest.repository.IGuestRepository;
import com.example.wedding.security.model.UserDto;
import com.example.wedding.security.repository.UserRepository;
import com.example.wedding.security.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GuestService {
    private final IGuestRepository guestRepository;
    private final UserRepository userRepository;

    public List<Guest> getGuestList() {
        return guestRepository.findAll();
    }

    public void addGuest(RegistrationRequest request, String username) {

        UserDto user = userRepository.findUser(username);

        if (user == null) {
            throw new UserNotFoundException("Current user["+username+"] session is not valid.");
        }

        Guest guest = Guest.builder()
                .fullName(request.getFullName())
                .userId(user.getId())
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
