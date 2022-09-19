package com.example.wedding.security.service;

import com.example.wedding.security.Role;
import com.example.wedding.security.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class UserDetailsMangerImp implements UserDetailsManager {
    private final UserRepository userRepository;

    public List<UserDetails> allUsers() {
        return userRepository.findAll();
    }
    @Override
    public void createUser(UserDetails user) {
        userRepository.saveUser(user.getUsername(), user.getPassword(), Role.USER.toString());
    }

    @Override
    public void updateUser(UserDetails user) {
        userRepository.updateUser(user.getUsername(), user.getPassword(), Role.USER.toString());
    }

    @Override
    public void deleteUser(String username) {
        userRepository.deleteUser(username);
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        log.debug("UserDetailsMangerImp.changePassword() is not implemented!");
    }

    @Override
    public boolean userExists(String username) {
        return userRepository.findUser(username) != null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUser(username);
    }
}
