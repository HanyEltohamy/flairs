package com.ft.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ft.dtos.UserDto;
import com.ft.model.User;
import com.ft.service.UserServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userServiceImpl;

    /**
     * Retrieves a user by their unique identifier.
     *
     * @param id The unique identifier of the user to retrieve.
     * @return A ResponseEntity containing the UserDto representation of the user.
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
        return new ResponseEntity<UserDto>(userServiceImpl.getUserById(id), HttpStatus.OK);
    }

    /**
     * Creates a new user based on the provided UserDto.
     *
     * @param userDto The UserDto containing the details of the new user to create.
     * @return A ResponseEntity containing the newly created User.
     */
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDto userDto) {
        return new ResponseEntity<User>(userServiceImpl.createUser(userDto), HttpStatus.CREATED);
    }

    /**
     * Updates an existing user with the provided details.
     *
     * @param user The updated user details.
     * @return A ResponseEntity containing the updated UserDto representation of the
     *         user.
     */
    @PutMapping
    public ResponseEntity<UserDto> updateUser(@RequestBody User user) {
        return new ResponseEntity<UserDto>(userServiceImpl.updateUser(user), HttpStatus.CREATED);
    }

    /**
     * Deletes a user by their unique identifier.
     *
     * @param id The unique identifier of the user to delete.
     * @return A ResponseEntity with no content if the deletion was successful.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userServiceImpl.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/names")
    public ResponseEntity<List<String>> findUsersNames() {
        return ResponseEntity.ok(userServiceImpl.findUsersNames());
    }
    @GetMapping("/search")
    public ResponseEntity<List<String>> findUsersNamesNative(@RequestParam String search) {
        return ResponseEntity.ok(userServiceImpl.findUsersNamesNative(search));
    }
}
