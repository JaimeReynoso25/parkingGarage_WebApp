package com.jaime.parkingGarage.model.entity;

import jakarta.persistence.*;

import java.util.UUID;

// tells JPA this maps to a database table
@Entity
@Table(name = "vehicles")
public class Vehicle {
    public Vehicle() {
    }

    public Vehicle(User user, String license_plate, String vehicleType) {
        this.user = user;
        this.licensePlate = licensePlate;
        this.vehicleType = vehicleType;
    }

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "license_plate", unique = true, nullable = false)
    private String licensePlate;

    @Column(name = "vehicle_type", nullable = false)
    private String vehicleType;

    //getters and setters

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }
}

