package com.jaime.parkingGarage.model.entity;

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
    private UUID id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private double balance;

    //getters and setters

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

