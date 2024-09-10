package com.ft.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ft.dtos.UserDto;
import com.ft.exception.UserNotFoundException;
import com.ft.model.User;
import com.ft.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Implements the UserService interface, providing methods for managing user
 * data.
 * 
 * This class is responsible for creating, retrieving, updating, and deleting
 * user
 * entities in the application. It interacts with the UserRepository to perform
 * CRUD operations on the user data.
 * 
 * The class is annotated with @Service, indicating that it is a Spring-managed
 * service component. It also uses Lombok annotations (@RequiredArgsConstructor
 * and @Slf4j) to generate boilerplate code for constructor injection and
 * logging.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    /**
     * Creates a new user based on the provided user DTO.
     *
     * @param userDto the user data transfer object containing the user information
     * @return the newly created user entity
     * @throws IllegalArgumentException if the user DTO is invalid (e.g. missing
     *                                  required fields)
     * @throws IllegalStateException    if a user with the same email already exists
     * @throws RuntimeException         if an unexpected error occurs during user
     *                                  creation
     */
    public User createUser(UserDto userDto) {
        try {
            // Validate input
            if (userDto == null || userDto.getName() == null || userDto.getMail() == null) {
                throw new IllegalArgumentException("Invalid user data");
            }

            // Check if user with the same email already exists
            if (userRepository.findByMail(userDto.getMail()).isPresent()) {
                throw new IllegalStateException("User with this email already exists");
            }

            User user = User.builder()
                    .name(userDto.getName())
                    .mail(userDto.getMail())
                    .build();

            User newUser = userRepository.save(user);
            log.info("User created successfully: {}", newUser);
            return newUser;
        } catch (IllegalArgumentException | IllegalStateException e) {
            log.error("Validation error while creating user: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Error while creating user: {}", e.getMessage());
            throw new RuntimeException("Failed to create user", e);
        }
    }

    /**
     * Retrieves a user by their unique identifier.
     *
     * @param userId the unique identifier of the user to retrieve
     * @return a DTO representing the retrieved user
     * @throws UserNotFoundException if a user with the given ID is not found
     */
    @Override
    public UserDto getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));
        return UserDto.builder().name(user.getName()).mail(user.getMail()).build();
    }

    /**
     * Updates an existing user with the provided user data.
     *
     * @param user the user data to update, including the user's ID, name, and
     *                email
     * @return a DTO representing the updated user
     * @throws UserNotFoundException   if a user with the given ID is not found
     * @throws IllegalArgumentException if the user DTO is invalid (e.g. missing
     *                                  required fields)
     * @throws RuntimeException         if an unexpected error occurs during user
     *                                  update
     */
    @Override
    public UserDto updateUser(User user) {

        try {
            // Validate input
            if (user == null || user.getId() == null || user.getName() == null || user.getMail() == null) {
                throw new IllegalArgumentException("Invalid user data");
            }

            // Check if user exists
            User existingUser = userRepository.findById(user.getId())
                    .orElseThrow(() -> new UserNotFoundException("User not found with id: " + user.getId()));

            // Update user details
            existingUser.setName(user.getName());
            existingUser.setMail(user.getMail());

            User updatedUser = userRepository.save(existingUser);
            log.info("User updated successfully: {}", updatedUser);

            return UserDto.builder()
                    .name(updatedUser.getName())
                    .mail(updatedUser.getMail())
                    .build();
        } catch (UserNotFoundException | IllegalArgumentException | IllegalStateException e) {
            log.error("Validation error while updating user: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Error while updating user: {}", e.getMessage());
            throw new RuntimeException("Failed to update user", e);
        }
    }

    /**
     * Deletes a user from the system.
     *
     * @param userId the ID of the user to delete
     * @throws UserNotFoundException if a user with the given ID is not found
     * @throws RuntimeException if an unexpected error occurs during user deletion
     */
    @Override
    public void deleteUser(Long userId) {
        try {
            // Check if user exists
            User existingUser = userRepository.findById(userId)
                    .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));

            // Delete the user
            userRepository.delete(existingUser);
            log.info("User deleted successfully: {}", existingUser);
        } catch (UserNotFoundException e) {
            log.error("User not found for deletion: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Error while deleting user: {}", e.getMessage());
            throw new RuntimeException("Failed to delete user", e);
        }

    }


    public List<String> findUsersNames() {
        return userRepository.findUsersNames();
    }
    public List<String> findUsersNamesNative(String search) {
        return userRepository.findUsersNamesNative(search);
    }
}
