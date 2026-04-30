package com.jaime.parkingGarage.dto;

import java.time.LocalDate;
import java.util.UUID;

public class CreateReservationRequest {

    public UUID boatId;
    public LocalDate startDate;
    public LocalDate endDate;

    public CreateReservationRequest(UUID boatId, LocalDate startDate, LocalDate endDate) {
        this.boatId = boatId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public UUID getBoatId() { return boatId; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }

    public void setBoatId(UUID boatId) { this.boatId = boatId; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
}