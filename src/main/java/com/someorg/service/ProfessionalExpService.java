package com.someorg.service;

import com.someorg.exception.ResourceNotFoundException;
import com.someorg.model.ProfessionalExperience;
import com.someorg.model.User;
import com.someorg.repository.ProfessionalExpRepository;
import com.someorg.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfessionalExpService {

    private final ProfessionalExpRepository professionalExpRepository;
    private final UserRepository userRepository;

    public ProfessionalExperience addExperience(String email, ProfessionalExperience exp) {
        User user = userRepository.findById(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));
        exp.setUser(user);
        return professionalExpRepository.save(exp);
    }

    public List<ProfessionalExperience> getExperienceByUser(String email) {
        User user = userRepository.findById(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));
        return professionalExpRepository.findByUser(user);
    }
}
