package com.jaime.parkingGarage.service;

import com.jaime.parkingGarage.model.entity.User;
import com.jaime.parkingGarage.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;

// marks this as a service layer
@Service
public class UserService {

    private final UserRepository userRepository;

    // constructor injection (Spring automatically provides the repository)
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(String email, String password){

        // checks if both fields have data
        if(email == null || password == null || email.isEmpty() || password.isEmpty()) {
            throw new IllegalArgumentException("Please enter both fields.");
        }

        //checks if it is in valid email format
        if (!validateEmail(email)) {
            throw new IllegalArgumentException("Please enter a valid email");
        }

        //checks if email already exists
        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Email already exists");
        }

        //creates a new user
        return userRepository.save(new User(email, password));

    }

    public Optional<User> authenticateUser(String email, String password){

        //finds user by email
        Optional<User> userOptional = userRepository.findByEmail(email);

        // if no user is found, login fails
        if (userOptional.isEmpty()) {
            return Optional.empty();
        }

        User user = userOptional.get();

        // if password is not correct, return empty
        if (!user.getPassword().equals(password)){
            return Optional.empty();
        }

        return Optional.of(user);
    }

    private boolean validateEmail(String email) {
        // Basic regex pattern that works for most use cases
        String s = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        return Pattern.compile(s)
                    .matcher(email)
                    .matches();
    }

    public User addFunds(UUID id, double addedfunds){

        //Find user in database by email
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // add funds to user
        user.setBalance(user.getBalance() + addedfunds);

        //save user to database
        return userRepository.save(user);
    }

    public double getBalance(UUID userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"))
                .getBalance();
    }
}
