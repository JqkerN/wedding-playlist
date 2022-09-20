package com.example.wedding.security.service;

import com.example.wedding.exception.UserAlreadyExistException;
import com.example.wedding.security.model.Role;
import com.example.wedding.security.dto.RegistrerUserRequest;
import com.example.wedding.security.model.UserDto;
import com.example.wedding.security.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public List<UserDto> allUsers() {
        return userRepository.findAll();
    }

    public void addUser(RegistrerUserRequest user) {
        if (userRepository.findUser(user.getUsername()) != null) {
            throw new UserAlreadyExistException("User by username["+user.getUsername()+"] already exist, cannot have multiple users with the same username.");
        }

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        userRepository.saveUser(user.getUsername(), encodedPassword, Role.USER.toString());
    }

    public void updateUser(RegistrerUserRequest user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        userRepository.saveUser(user.getUsername(), encodedPassword, Role.USER.toString());
    }

    public void removeUser(Integer id) {
        userRepository.deleteUserById(id);
    }
}
