package com.ft.service;

import com.ft.dtos.UserDto;
import com.ft.model.User;

public interface UserService {
    User createUser(UserDto userDto);

    UserDto getUserById(Long userId);

    UserDto updateUser(User user);

    void deleteUser(Long userId);
}
