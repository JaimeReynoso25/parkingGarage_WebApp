package com.jaime.parkingGarage.controller;

import com.jaime.parkingGarage.config.JwtUtil;
import com.jaime.parkingGarage.dto.AddFundsRequest;
import com.jaime.parkingGarage.model.entity.User;
import com.jaime.parkingGarage.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    public UserController(UserService userSrvice, JwtUtil jwtUtil) {
        this.userService = userSrvice;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/addFunds")
    public User addFunds(
            @RequestHeader("Authorization") String token,
            @RequestBody AddFundsRequest request)
    {
        String cleanToken = token.substring(7);
        String email = jwtUtil.extractEmail(cleanToken);
        return userService.addFunds(token, request.getAmount());
    }

}
