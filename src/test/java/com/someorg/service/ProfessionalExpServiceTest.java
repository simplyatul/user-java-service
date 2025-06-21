package com.someorg.service;

import com.someorg.exception.ResourceNotFoundException;
import com.someorg.model.ProfessionalExperience;
import com.someorg.model.User;
import com.someorg.repository.ProfessionalExpRepository;
import com.someorg.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProfessionalExpServiceTest {

    @InjectMocks
    private ProfessionalExpService professionalExpService;

    @Mock
    private ProfessionalExpRepository professionalExpRepository;

    @Mock
    private UserRepository userRepository;

    private User user;
    private ProfessionalExperience experience;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User("test@example.com", "Test", "User");
        experience = new ProfessionalExperience(null, user, "Google", "SDE",
                          LocalDate.of(2015, 1, 1), LocalDate.of(2020, 1, 1), false);
    }

    @Test
    void addExperience_success() {
        when(userRepository.findById(user.getEmailId())).thenReturn(Optional.of(user));
        when(professionalExpRepository.save(any(ProfessionalExperience.class))).thenReturn(experience);

        ProfessionalExperience result = professionalExpService.addExperience(user.getEmailId(), experience);
        assertEquals("Google", result.getOrgName());
        verify(professionalExpRepository).save(experience);
    }

    @Test
    void addExperience_userNotFound() {
        when(userRepository.findById(user.getEmailId())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class,
                     () -> professionalExpService.addExperience(user.getEmailId(), experience));
    }

    @Test
    void getExperienceByUser_success() {
        when(userRepository.findById(user.getEmailId())).thenReturn(Optional.of(user));
        when(professionalExpRepository.findByUser(user)).thenReturn(List.of(experience));

        List<ProfessionalExperience> result = professionalExpService.getExperienceByUser(user.getEmailId());
        assertEquals(1, result.size());
    }

    @Test
    void getExperienceByUser_userNotFound() {
        when(userRepository.findById(user.getEmailId())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class,
                     () -> professionalExpService.getExperienceByUser(user.getEmailId()));
    }
}
