package com.jaime.parkingGarage.dto;

// DTO for adding funds
public class AddFundsRequest {

    public AddFundsRequest() {
    }

    public AddFundsRequest(double amount) {
        this.amount = amount;
    }

    private double amount;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
