package com.jaime.parkingGarage.model.entity;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "boats")
public class Boat {

    @Id
    @GeneratedValue
    private UUID boatId;

    @Column(nullable = false)
    private String boatType;

    @Column(nullable = false)
    private double pricePerDay;

    public Boat() {}

    public Boat(String boatType, double pricePerDay) {
        this.boatType = boatType;
        this.pricePerDay = pricePerDay;
    }

    public UUID getBoatId() {
        return boatId;
    }

    public String getBoatType() {
        return boatType;
    }

    public void setBoatType(String boatType) {
        this.boatType = boatType;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }
}