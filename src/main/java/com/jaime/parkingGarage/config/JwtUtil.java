package com.jaime.parkingGarage.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import java.security.Key;
import java.util.Date;
import java.util.UUID;

@Component
public class JwtUtil {

    // pulls value from application.yml
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    private Key key;

    // This runs once when the app starts
    // Converts  string secret into a secure Key object
    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    //generate token after login
    public String generateToken(UUID userID) {
        return Jwts.builder()
                .setSubject(userID.toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(key)
                .compact();
    }

    //extract userID from token
    public UUID extractUserId(String token) {
        String subject = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();

        return UUID.fromString(subject);
    }

    //validate token
    public boolean validateToken(String token){
        try {
            extractUserId(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}