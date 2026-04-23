package com.jaime.parkingGarage.repository;

import com.jaime.parkingGarage.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    // sql query to find if an email exists
    Optional<User> findByEmail(String email);

    //sql query to find if an email is unique
    boolean existsByEmail(String email);
}
