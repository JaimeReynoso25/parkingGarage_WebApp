package com.jaime.parkingGarage.controller;

import com.jaime.parkingGarage.dto.CreateReservationRequest;
import com.jaime.parkingGarage.model.entity.Reservation;
import com.jaime.parkingGarage.service.ReservationService;
import com.jaime.parkingGarage.config.JwtUtil;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;
    private final JwtUtil jwtUtil;

    public ReservationController(ReservationService reservationService, JwtUtil jwtUtil) {
        this.reservationService = reservationService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/create")
    public Reservation createReservation(
            @RequestHeader("Authorization") String token,
            @RequestBody CreateReservationRequest request
    ) {
        String cleanToken = token.substring(7);
        UUID userId = jwtUtil.extractUserId(cleanToken);

        return reservationService.createReservation(userId, request);
    }

    @PostMapping("/cancel/{reservationId}")
    public Reservation cancelReservation(
            @RequestHeader("Authorization") String token,
            @RequestParam UUID reservationId
    ) {
        String cleanToken = token.substring(7);
        UUID userId = jwtUtil.extractUserId(cleanToken);

        return reservationService.cancelReservation(userId, reservationId);

    }
}