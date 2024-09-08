package com.ft.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ft.dtos.UserDto;
import com.ft.model.User;
import com.ft.repository.UserRepository;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testFindUserById() {
        User user = new User(1L, "testUser", "test@example.com");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        UserDto result = userService.getUserById(1L);
        assertEquals("testUser", result.getName());
    }
    @Test
    void testSaveUser() {
        User userToSave = new User(null, "newUser", "new@example.com");
        User savedUser = new User(1L, "newUser", "new@example.com");
        when(userRepository.save(userToSave)).thenReturn(savedUser);
        UserDto userDto = UserDto.builder().name(savedUser.getName()).mail(savedUser.getMail()).build();
        User result = userService.createUser(userDto);

        assertNotNull(result.getId());
        assertEquals("newUser", result.getName());
    }

}
