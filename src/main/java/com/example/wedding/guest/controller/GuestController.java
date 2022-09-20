package com.example.wedding.guest.controller;

import com.example.wedding.guest.dto.RegistrationRequest;
import com.example.wedding.guest.model.Guest;
import com.example.wedding.guest.service.GuestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Api(tags={"Guest List Controller"})
@RestController
@RequestMapping("/api/v1/guests")
@AllArgsConstructor
public class GuestController {
    private final GuestService guestService;

    @ApiOperation(value = "Get all registered guests.")
    @GetMapping
    public List<Guest> getAllGuests() {
        return guestService.getGuestList();
    }

    @ApiOperation(value = "Search for guest with a specific id.")

    @GetMapping("/{id}")
    public Guest getGuest(@PathVariable Integer id) {
        return guestService.getGuest(id);
    }

    @ApiOperation(value = "Register a guest.")
    @PostMapping
    public void registerGuest(@Valid @RequestBody RegistrationRequest request) {
        guestService.addGuest(request);
    }

    @ApiOperation(value = "Unregister a guest by id.")
    @DeleteMapping("/{id}")
    public void unregisterGuest(@PathVariable Integer id) {
        guestService.removeGuest(id);
    }
}
