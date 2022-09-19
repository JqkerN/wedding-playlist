package com.example.wedding.security.controller;

import com.example.wedding.security.dto.RegistrerUserRequest;
import com.example.wedding.security.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Profile("dev")
@RestController
@RequestMapping("/admin/api/v1/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<UserDetails> allUsers(){
        return userService.allUsers();
    }

    @PostMapping
    public void addUser(@Valid @RequestBody RegistrerUserRequest request){
        userService.addUser(request);
    }

    @PutMapping
    public void updateUser(@Valid @RequestBody RegistrerUserRequest request){
        userService.updateUser(request);
    }

    @DeleteMapping("/{id}")
    public void removeTrack(@PathVariable() Integer id) {
        userService.removeUser(id);
    }
}
