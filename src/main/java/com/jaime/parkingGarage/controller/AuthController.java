package com.jaime.parkingGarage.controller;

import com.jaime.parkingGarage.config.JwtUtil;
import com.jaime.parkingGarage.dto.LoginRequest;
import com.jaime.parkingGarage.dto.RegisterRequest;
import com.jaime.parkingGarage.model.entity.User;
import com.jaime.parkingGarage.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    //constructor injection (Spring automatically provides the repository)
    public AuthController(UserService userService, JwtUtil jwtUtil){
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
}
