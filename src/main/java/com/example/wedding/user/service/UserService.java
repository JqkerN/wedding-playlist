package com.example.wedding.user.service;

import com.example.wedding.exception.UserAlreadyExistException;
import com.example.wedding.user.model.Role;
import com.example.wedding.user.dto.RegistrerUserRequest;
import com.example.wedding.user.model.UserDto;
import com.example.wedding.user.repository.UserRepository;
import lombok.AllArgsConstructor;
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
