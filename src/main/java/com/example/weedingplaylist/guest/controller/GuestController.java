package com.example.weedingplaylist.guest.controller;

import com.example.weedingplaylist.guest.dto.RegistrationRequest;
import com.example.weedingplaylist.guest.model.Guest;
import com.example.weedingplaylist.guest.service.GuestService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/guests")
@AllArgsConstructor
public class GuestController {
    private final GuestService guestService;

    @GetMapping
    public List<Guest> getAllGuests() {
        return guestService.getGuestList();
    }

    @GetMapping("/{id}")
    public Guest getGuest(@PathVariable Integer id) {
        return guestService.getGuest(id);
    }

    @PostMapping
    public void registerGuest(@Valid @RequestBody RegistrationRequest request) {
        guestService.addGuest(request);
    }

    @DeleteMapping("/{id}")
    public void unregisterGuest(@PathVariable Integer id) {
        guestService.removeGuest(id);
    }
}
