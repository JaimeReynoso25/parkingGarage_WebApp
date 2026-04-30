package com.jaime.parkingGarage.service;

import com.jaime.parkingGarage.model.entity.User;
import com.jaime.parkingGarage.model.entity.Vehicle;
import com.jaime.parkingGarage.repository.UserRepository;
import com.jaime.parkingGarage.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    private final UserRepository userRepository;

    public VehicleService(VehicleRepository vehicleRepository,UserRepository userRepository) {
        this.vehicleRepository = vehicleRepository;
        this.userRepository = userRepository;
    }

    public Vehicle registerCar(UUID userId, String licencePlate, String vehicleType) {

        // checks if both fields have data
        if (licencePlate == null || licencePlate.isBlank() || vehicleType == null || vehicleType.isBlank()){
            throw new IllegalArgumentException("Please enter both fields");
        }

        // check if licence plate already exists
        if (vehicleRepository.existsBylicencePlate(licencePlate)) {
            throw new IllegalArgumentException("licence plate already exists, please use a unique licence plate");
        }

        //Find user in database by id
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // creates a new vehicle in the table
        return vehicleRepository.save(new Vehicle(user, licencePlate, vehicleType));
    }

    public void deleteVehicle(UUID userId, String licencePlate){

        if (licencePlate == null || licencePlate.isBlank()) {
            throw new IllegalArgumentException("Licence plate is required");
        }

        Vehicle vehicle = vehicleRepository
                .findByLicencePlateAndUser_Id(licencePlate, userId)
                .orElseThrow(() -> new RuntimeException("Vehicle not found for this user"));

        vehicleRepository.delete(vehicle);

    }

    public List<Vehicle> getVehicles(UUID userId) {
        return vehicleRepository.findByUser_Id(userId);
    }

}