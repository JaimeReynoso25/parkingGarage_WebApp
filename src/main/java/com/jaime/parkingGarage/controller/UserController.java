package com.jaime.parkingGarage.controller;

import com.jaime.parkingGarage.config.JwtUtil;
import com.jaime.parkingGarage.dto.AddFundsRequest;
import com.jaime.parkingGarage.model.entity.User;
import com.jaime.parkingGarage.service.UserService;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/addFunds")
    public User addFunds(
            @RequestHeader("Authorization") String token,
            @RequestBody AddFundsRequest request)
    {
        String cleanToken = token.substring(7);
        UUID userId = jwtUtil.extractUserId(cleanToken);
        return userService.addFunds(userId, request.getAmount());
    }
}
