package com.jaime.parkingGarage.repository;

import com.jaime.parkingGarage.model.entity.Boat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BoatRepository extends JpaRepository<Boat, UUID> {
}