package com.someorg.tests.service;

import com.someorg.exception.ResourceNotFoundException;
import com.someorg.model.Education;
import com.someorg.model.User;
import com.someorg.repository.EducationRepository;
import com.someorg.repository.UserRepository;
import com.someorg.service.EducationService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EducationServiceTest {

    @InjectMocks
    private EducationService educationService;

    @Mock
    private EducationRepository educationRepository;

    @Mock
    private UserRepository userRepository;

    private User user;
    private Education education;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User("test@example.com", "Test", "User");
        education = new Education(null, user, "MIT", "BSc", "https://mit.edu",
                                  LocalDate.of(2010, 1, 1), LocalDate.of(2014, 1, 1), false);
    }

    @Test
    void addEducation_success() {
        when(userRepository.findById(user.getEmailId())).thenReturn(Optional.of(user));
        when(educationRepository.save(any(Education.class))).thenReturn(education);

        Education result = educationService.addEducation(user.getEmailId(), education);
        assertEquals("MIT", result.getInstituteName());
        verify(educationRepository).save(education);
    }

    @Test
    void addEducation_userNotFound() {
        when(userRepository.findById(user.getEmailId())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class,
                     () -> educationService.addEducation(user.getEmailId(), education));
    }

    @Test
    void getEducationByUser_success() {
        when(userRepository.findById(user.getEmailId())).thenReturn(Optional.of(user));
        when(educationRepository.findByUser(user)).thenReturn(List.of(education));

        List<Education> result = educationService.getEducationByUser(user.getEmailId());
        assertEquals(1, result.size());
    }

    @Test
    void getEducationByUser_userNotFound() {
        when(userRepository.findById(user.getEmailId())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class,
                     () -> educationService.getEducationByUser(user.getEmailId()));
    }
}
