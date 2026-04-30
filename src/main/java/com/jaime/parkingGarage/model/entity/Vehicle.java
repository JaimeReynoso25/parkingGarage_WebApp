package com.jaime.parkingGarage.model.entity;

import jakarta.persistence.*;

import java.util.UUID;

// tells JPA this maps to a database table
@Entity
@Table(name = "vehicles")
public class Vehicle {
    public Vehicle() {
    }

    public Vehicle(User user, String licencePlate, String vehicleType) {
        this.user = user;
        this.licencePlate = licencePlate;
        this.vehicleType = vehicleType;
    }

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "licence_plate", unique = true, nullable = false)
    private String licencePlate;

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

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }
}