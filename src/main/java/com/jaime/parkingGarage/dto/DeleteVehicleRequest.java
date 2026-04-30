package com.jaime.parkingGarage.dto;

public class DeleteVehicleRequest {

    private String licencePlate;

    public DeleteVehicleRequest(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }
}
