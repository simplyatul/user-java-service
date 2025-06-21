package com.someorg.service;

import com.someorg.exception.ResourceNotFoundException;
import com.someorg.exception.UserAlreadyExistsException;
import com.someorg.model.User;
import com.someorg.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    private User mockUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockUser = new User("test@example.com", "Test", "User", null, null);
    }

    @Test
    void createUser_success() {
        when(userRepository.existsById(mockUser.getEmailId())).thenReturn(false);
        when(userRepository.save(mockUser)).thenReturn(mockUser);

        User created = userService.createUser(mockUser);
        assertEquals("test@example.com", created.getEmailId());
        verify(userRepository).save(mockUser);
    }

    @Test
    void createUser_alreadyExists() {
        when(userRepository.existsById(mockUser.getEmailId())).thenReturn(true);
        assertThrows(UserAlreadyExistsException.class, () -> userService.createUser(mockUser));
    }

    @Test
    void getUser_success() {
        when(userRepository.findById("test@example.com")).thenReturn(Optional.of(mockUser));
        User user = userService.getUserByEmail("test@example.com");
        assertEquals("Test", user.getFirstName());
    }

    @Test
    void getUser_notFound() {
        when(userRepository.findById("unknown@example.com")).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> userService.getUserByEmail("unknown@example.com"));
    }

    @Test
    void deleteUser_success() {
        when(userRepository.findById("test@example.com")).thenReturn(Optional.of(mockUser));
        userService.deleteUser("test@example.com");
        verify(userRepository).delete(mockUser);
    }
}
