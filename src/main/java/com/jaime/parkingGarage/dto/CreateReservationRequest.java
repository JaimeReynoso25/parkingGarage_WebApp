package com.jaime.parkingGarage.dto;

import java.time.LocalDate;
import java.util.UUID;

public class CreateReservationRequest {

    public UUID vehicleId;
    public String spotId;
    public LocalDate startDate;
    public LocalDate endDate;

    public CreateReservationRequest(UUID vehicleId, String spotId, LocalDate startDate, LocalDate endDate) {
        this.vehicleId = vehicleId;
        this.spotId = spotId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public UUID getVehicleId() { return vehicleId; }
    public String getSpotId() { return spotId; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }

    public void setVehicleId(UUID vehicleId) { this.vehicleId = vehicleId; }
    public void setSpotId(String spotId) { this.spotId = spotId; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
}