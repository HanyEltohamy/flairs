package com.ft.Controller;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ft.controller.UserController;
import com.ft.dtos.UserDto;
import com.ft.model.User;
import com.ft.service.UserServiceImpl;

class UserControllerTest {

    @Mock
    private UserServiceImpl userServiceImpl;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUser() {
        Long userId = 1L;
        UserDto userDto = new UserDto();
        when(userServiceImpl.getUserById(userId)).thenReturn(userDto);

        ResponseEntity<UserDto> response = userController.getUser(userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userDto, response.getBody());
        verify(userServiceImpl).getUserById(userId);
    }

    @Test
    void testCreateUser() {
        UserDto userDto = new UserDto();
        User createdUser = new User();
        when(userServiceImpl.createUser(userDto)).thenReturn(createdUser);

        ResponseEntity<User> response = userController.createUser(userDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(createdUser, response.getBody());
        verify(userServiceImpl).createUser(userDto);
    }

    @Test
    void testUpdateUser() {
        User user = new User();
        UserDto updatedUserDto = new UserDto();
        when(userServiceImpl.updateUser(user)).thenReturn(updatedUserDto);

        ResponseEntity<UserDto> response = userController.updateUser(user);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(updatedUserDto, response.getBody());
        verify(userServiceImpl).updateUser(user);
    }

    @Test
    void testDeleteUser() {
        Long userId = 1L;

        ResponseEntity<Void> response = userController.deleteUser(userId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
        verify(userServiceImpl).deleteUser(userId);
    }
}
