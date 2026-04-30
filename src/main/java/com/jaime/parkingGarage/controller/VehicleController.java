package com.jaime.parkingGarage.controller;


import com.jaime.parkingGarage.config.JwtUtil;
import com.jaime.parkingGarage.dto.DeleteVehicleRequest;
import com.jaime.parkingGarage.dto.RegisterVehicleRequest;
import com.jaime.parkingGarage.model.entity.Vehicle;
import com.jaime.parkingGarage.service.VehicleService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    private final VehicleService vehicleService;
    private final JwtUtil jwtUtil;

    public VehicleController(VehicleService vehicleService, JwtUtil jwtUtil) {
        this.vehicleService = vehicleService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/registerVehicle")
    public Vehicle registerVehicle(
            @RequestHeader("Authorization") String token,
            @RequestBody RegisterVehicleRequest request)
    {
        String cleanToken = token.substring(7);
        UUID userID = jwtUtil.extractUserId(cleanToken);
        return vehicleService.registerCar(userID, request.getLicencePlate(), request.getVehicleType());

    }

    @DeleteMapping("/deleteVehicle")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteVehicle(
            @RequestHeader("Authorization") String token,
            @RequestBody DeleteVehicleRequest request) {

        String cleanToken = token.substring(7);
        UUID userId = jwtUtil.extractUserId(cleanToken);

        vehicleService.deleteVehicle(userId, request.getLicencePlate());
    }

    @GetMapping("/getVehicles")
    public List<Vehicle> getVehicles(@RequestHeader("Authorization") String token) {
        String cleanToken = token.substring(7);
        UUID userId = jwtUtil.extractUserId(cleanToken);

        return vehicleService.getVehicles(userId);
    }
}
