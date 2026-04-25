package com.jaime.parkingGarage.service;

import com.jaime.parkingGarage.config.JwtUtil;
import com.jaime.parkingGarage.model.entity.User;
import com.jaime.parkingGarage.model.entity.Vehicle;
import com.jaime.parkingGarage.repository.UserRepository;
import com.jaime.parkingGarage.repository.VehicleRepository;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    private final UserRepository userRepository;

    public VehicleService(VehicleRepository vehicleRepository,UserRepository userRepository) {
        this.vehicleRepository = vehicleRepository;
        this.userRepository = userRepository;
    }

    public String registerCar(String email, String licensePlate, String vehicleType) {

        // checks if both fields have data
        if (licensePlate == null || licensePlate.isBlank() || vehicleType == null || vehicleType.isBlank()){
            return "Please enter both fields";
        }

        // check if license plate already exists
        if (vehicleRepository.existsByLicensePlate(licensePlate)) {
            return "License plate already exists, please use a unique license plate";
        }

        //Find user in database by email
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // creates a new vehicle in the table
        vehicleRepository.save(new Vehicle(user, licensePlate, vehicleType));

        return "Vehicle registered successfully";
    }
}