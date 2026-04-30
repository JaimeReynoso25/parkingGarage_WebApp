package com.jaime.parkingGarage.dto;

public class BoatRequest {

    private String boatType;
    private double pricePerDay;

    public BoatRequest() {}

    public BoatRequest(String boatType, double pricePerDay) {
        this.boatType = boatType;
        this.pricePerDay = pricePerDay;
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