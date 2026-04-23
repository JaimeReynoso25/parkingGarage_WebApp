package com.jaime.parkingGarage.controllers;

import com.jaime.parkingGarage.dto.LoginRequest;
import com.jaime.parkingGarage.dto.RegisterRequest;
import com.jaime.parkingGarage.repository.UserRepository;
import com.jaime.parkingGarage.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    //constructor injection (Spring automatically provides the repository)
    public AuthController(UserService userSerive){
        this.userService = userSerive;
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        return userService.registerUser(request.getEmail(), request.getPassword());
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        if (userService.authenticateUser(request.getEmail(), request.getPassword())) {
            return "Login successful";
        } else {
            return "Invalid email or password";
        }
    }


}
