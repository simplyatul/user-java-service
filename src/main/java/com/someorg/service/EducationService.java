package com.someorg.service;

import com.someorg.exception.ResourceNotFoundException;
import com.someorg.model.Education;
import com.someorg.model.User;
import com.someorg.repository.EducationRepository;
import com.someorg.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EducationService {

    private final EducationRepository educationRepository;
    private final UserRepository userRepository;

    public Education addEducation(String email, Education edu) {
        User user = userRepository.findById(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));
        edu.setUser(user);
        return educationRepository.save(edu);
    }

    public List<Education> getEducationByUser(String email) {
        User user = userRepository.findById(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));
        return educationRepository.findByUser(user);
    }
}
