package com.example.wedding.security.controller;

import com.example.wedding.security.dto.RegistrerUserRequest;
import com.example.wedding.security.model.UserDto;
import com.example.wedding.security.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;

@ApiIgnore
@RestController
@RequestMapping("/admin/api/v1/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<UserDto> allUsers(){
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
