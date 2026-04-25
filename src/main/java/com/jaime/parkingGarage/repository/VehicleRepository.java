package com.jaime.parkingGarage.repository;

import com.jaime.parkingGarage.model.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VehicleRepository extends JpaRepository<Vehicle, UUID> {

    // sql query to check if the license plate already exists in the database
    boolean existsByLicensePlate(String licensePlate);
}
