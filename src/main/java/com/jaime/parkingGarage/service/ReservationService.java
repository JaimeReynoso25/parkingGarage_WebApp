package com.jaime.parkingGarage.service;

import com.jaime.parkingGarage.dto.CreateReservationRequest;
import com.jaime.parkingGarage.model.entity.Reservation;
import com.jaime.parkingGarage.model.entity.ReservationStatus;
import com.jaime.parkingGarage.model.entity.User;
import com.jaime.parkingGarage.repository.ReservationRepository;
import com.jaime.parkingGarage.repository.UserRepository;
import com.jaime.parkingGarage.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.UUID;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final VehicleRepository vehicleRepository;

    public ReservationService(ReservationRepository reservationRepository,
                              UserRepository userRepository,
                              VehicleRepository vehicleRepository) {
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
        this.vehicleRepository = vehicleRepository;
    }

    public Reservation createReservatiion(UUID userId, CreateReservationRequest request) {

        // 1. Check spot availability (overlapping reservations)
        boolean spotTaken = !reservationRepository
                .findBySpotIdAndStatusNotAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
                        request.getSpotId(),
                        ReservationStatus.CANCELLED,
                        request.getEndDate(),
                        request.getStartDate()
                )
                .isEmpty();

        if (spotTaken) {
            throw new RuntimeException("Spot is already reserved for selected dates");
        }

        // 2. Calculate total days (inclusive)
        int totalDays = (int) ChronoUnit.DAYS.between(
                request.getStartDate(),
                request.getEndDate()
        ) + 1;

        // 3. Calculate cost ($10 per day)
        double totalCost = totalDays * 10.0;

        // 4. Get user and check balance
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getBalance() < totalCost) {
            throw new RuntimeException("Insufficient funds");
        }

        // 5. Deduct funds
        user.setBalance(user.getBalance() - totalCost);
        userRepository.save(user);

        // 6. Create reservation
        Reservation reservation = new Reservation();
        reservation.setUserId(userId);
        reservation.setVehicleId(request.getVehicleId());
        reservation.setSpotId(request.getSpotId());
        reservation.setStartDate(request.getStartDate());
        reservation.setEndDate(request.getEndDate());
        reservation.setTotalDays(totalDays);
        reservation.setTotalCost(totalCost);
        reservation.setStatus(ReservationStatus.ACTIVE);

        // 7. Save reservation
        return reservationRepository.save(reservation);
    }
}