package com.jaime.parkingGarage.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.UUID;

// tells JPA this maps to a database table
@Entity
@Table(name = "users")
public class User {
    public User() {
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
        this.balance = 0;
    }

    @Id
    @GeneratedValue // auto-generates the primary key
    @Column(name = "user_id")
    private UUID userId;

    @Column(unique = true, nullable = false)
    private String email;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    private double balance;

    //getters and setters

    public UUID getId() {
        return userId;
    }

    public void setId(UUID userId) {
        this.userId = userId;
    }

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

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}

