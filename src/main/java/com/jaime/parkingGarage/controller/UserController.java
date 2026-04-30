package com.jaime.parkingGarage.controller;

import com.jaime.parkingGarage.config.JwtUtil;
import com.jaime.parkingGarage.dto.AddFundsRequest;
import com.jaime.parkingGarage.dto.LoginRequest;
import com.jaime.parkingGarage.dto.RegisterRequest;
import com.jaime.parkingGarage.model.entity.User;
import com.jaime.parkingGarage.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    public UserController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    //registers a user
    @PostMapping("/register")
    public User register(@RequestBody RegisterRequest request) {
        return userService.registerUser(
                request.getEmail(),
                request.getPassword()
        );
    }

    //login and authenticates user with JWT
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {

        Optional<User> userOptional = userService.authenticateUser(
                request.getEmail(),
                request.getPassword()
        );

        if (userOptional.isPresent()) {
            return jwtUtil.generateToken(userOptional.get().getId());
        }

        return "Invalid email or password";
    }

    @PostMapping("/addFunds")
    public User addFunds(
            @RequestHeader("Authorization") String token,
            @RequestBody AddFundsRequest request)
    {
        String cleanToken = token.substring(7);
        UUID userId = jwtUtil.extractUserId(cleanToken);
        return userService.addFunds(userId, request.getAmount());
    }

    @GetMapping("/balance")
    public double getBalance(@RequestHeader("Authorization") String token) {
        String cleanToken = token.substring(7);
        UUID userId = jwtUtil.extractUserId(cleanToken);
        return userService.getBalance(userId);
    }
}
