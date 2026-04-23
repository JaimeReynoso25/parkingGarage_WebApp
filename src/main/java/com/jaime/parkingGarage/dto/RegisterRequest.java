package com.jaime.parkingGarage.dto;

public class RegisterRequest {

    public RegisterRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    private String email;
    private String password;

    // getters and setters
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
