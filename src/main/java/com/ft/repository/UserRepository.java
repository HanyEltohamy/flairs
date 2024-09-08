package com.ft.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ft.model.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Finds a {@link User} by their email address.
     *
     * @param email the email address to search for
     * @return an {@link Optional} containing the {@link User} if found, or an empty {@link Optional} if not found
     */
    Optional<User> findByMail(String email);
}
