package com.jaime.parkingGarage.controllers;

import com.jaime.parkingGarage.config.JwtUtil;
import com.jaime.parkingGarage.dto.LoginRequest;
import com.jaime.parkingGarage.dto.RegisterRequest;
import com.jaime.parkingGarage.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public String register(@RequestBody RegisterRequest request) {
        return userService.registerUser(
                request.getEmail(),
                request.getPassword()
        );
    }

    //login and authenticates user with JWT
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {

        boolean authenticated = userService.authenticateUser(
                request.getEmail(),
                request.getPassword()
        );

        if (authenticated) {
            //generate JWT using email and returns it
            String token = jwtUtil.generateToken(request.getEmail());
            return token;
        }
        return "Invalid email or password";
    }
}
