package com.jaime.parkingGarage.dto;

public class RegisterVehicleRequest {

    public RegisterVehicleRequest() {}

    public RegisterVehicleRequest(String vehicleType, String licencePlate){
        this.vehicleType = vehicleType;
        this.licencePlate = licencePlate;
    }

    private String vehicleType;
    private String licencePlate;

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }
}
