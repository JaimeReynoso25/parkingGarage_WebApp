package com.jaime.parkingGarage.repository;

import com.jaime.parkingGarage.model.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VehicleRepository extends JpaRepository<Vehicle, UUID> {

    // sql query to check if the licence plate already exists in the database
    boolean existsBylicencePlate(String licencePlate);

    // sql query to delete a licencePlate
    Optional<Vehicle> findByLicencePlateAndUser_Id(String licencePlate, UUID userId);

    // shows a list of all vehicles
    List<Vehicle> findByUser_Id(UUID userId);
}
