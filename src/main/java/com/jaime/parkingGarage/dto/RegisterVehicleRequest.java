package com.jaime.parkingGarage.dto;

public class RegisterVehicleRequest {

    public RegisterVehicleRequest(String vehicleType, String licensePlate){
        this.vehicleType = vehicleType;
        this.licensePlate = licensePlate;

    }

    private String vehicleType;
    private String licensePlate;

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }
}
