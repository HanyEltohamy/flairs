package com.ft.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.ft.repository.UserRepository;
import com.ft.model.User;

import java.util.Optional;
import java.util.List;
import java.util.Arrays;

public class UserRepositoryTest {

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindById() {
        User user = new User(1L, "John Doe", "john@example.com");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        Optional<User> result = userRepository.findById(1L);

        assertTrue(result.isPresent());
        assertEquals(user, result.get());
    }

    @Test
    void testFindAll() {
        List<User> users = Arrays.asList(
                new User(1L, "John Doe", "john@example.com"),
                new User(2L, "Jane Smith", "jane@example.com"));
        when(userRepository.findAll()).thenReturn(users);

        List<User> result = userRepository.findAll();

        assertEquals(2, result.size());
        assertEquals(users, result);
    }

    @Test
    void testCreateUser() {
        User user = new User(null, "New User", "newuser@example.com");
        User savedUser = new User(1L, "New User", "newuser@example.com");
        when(userRepository.save(user)).thenReturn(savedUser);

        User result = userRepository.save(user);

        assertNotNull(result.getId());
        assertEquals(savedUser, result);
    }

    @Test
    void testDeleteById() {
        doNothing().when(userRepository).deleteById(1L);

        userRepository.deleteById(1L);

        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    void testFindByEmail() {
        User user = new User(1L, "John Doe", "john@example.com");
        when(userRepository.findByMail("john@example.com")).thenReturn(Optional.of(user));

        Optional<User> result = userRepository.findByMail("john@example.com");

        assertTrue(result.isPresent());
        assertEquals(user, result.get());
    }
}
