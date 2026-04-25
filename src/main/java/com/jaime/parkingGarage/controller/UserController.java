package com.jaime.parkingGarage.controller;

import com.jaime.parkingGarage.dto.AddFundsRequest;
import com.jaime.parkingGarage.model.entity.User;
import com.jaime.parkingGarage.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userSrvice) {
        this.userService = userSrvice;
    }

    @PostMapping("/addFunds")
    public User addFunds(
            @RequestHeader("Authorization") String token,
            @RequestBody AddFundsRequest request)
    {
        return userService.addFunds(token, request.getAmount());
    }

}
