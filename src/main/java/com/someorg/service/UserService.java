package com.someorg.service;

import com.someorg.exception.ResourceNotFoundException;
import com.someorg.exception.UserAlreadyExistsException;
import com.someorg.model.User;
import com.someorg.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User createUser(User user) {
        if (userRepository.existsById(user.getEmailId())) {
            throw new UserAlreadyExistsException("User already exists with email: " + user.getEmailId());
        }
        return userRepository.save(user);
    }

    public User getUserByEmail(String email) {
        return userRepository.findById(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));
    }

    public void deleteUser(String email) {
        User user = getUserByEmail(email);
        userRepository.delete(user);
    }
}
