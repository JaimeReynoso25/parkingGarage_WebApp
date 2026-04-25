package com.jaime.parkingGarage.service;

import com.jaime.parkingGarage.config.JwtUtil;
import com.jaime.parkingGarage.model.entity.User;
import com.jaime.parkingGarage.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Pattern;

// marks this as a service layer
@Service
public class UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    // constructor injection (Spring automatically provides the repository)
    public UserService(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    public String registerUser(String email, String password){

        // checks if both fields have data
        if(email == null || password == null || email.isEmpty() || password.isEmpty()) {
            return "Please enter both fields.";
        }

        //checks if it is in valid email format
        if (!validateEmail(email)) {
            return "Please enter a valid email";
        }

        //checks if email already exists
        if (userRepository.existsByEmail(email)) {
            return "Email already exists";
        }

        //creates a new user
        userRepository.save(new User(email, password));

        return "User registered successfully"; // Added a return statement for successful registration
    }

    public boolean authenticateUser(String email, String password){

        //finds user by email
        Optional<User> userOptional = userRepository.findByEmail(email);

        // if no user is found, login fails
        if (userOptional.isEmpty()) {
            return false;
        }

        User user = userOptional.get();

        //checks password
        return user.getPassword().equals(password);
    }

    private boolean validateEmail(String email) {
        // Basic regex pattern that works for most use cases
        String s = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        return Pattern.compile(s)
                    .matcher(email)
                    .matches();
    }

    public User addFunds(String token, double addedfunds){

        // remove "Bearer " prefix from header
        String cleanToken = token.substring(7);
        
        //extract email from JWT
        String email = jwtUtil.extractEmail(cleanToken);

        //Find user in database by email
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // add funds to user
        user.setBalance(user.getBalance() + addedfunds);

        //save user to database
        return userRepository.save(user);
    }

}
