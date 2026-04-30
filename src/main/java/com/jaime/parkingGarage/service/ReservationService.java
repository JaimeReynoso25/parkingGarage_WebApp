package com.jaime.parkingGarage.service;

import com.jaime.parkingGarage.dto.CreateReservationRequest;
import com.jaime.parkingGarage.model.entity.Boat;
import com.jaime.parkingGarage.model.entity.Reservation;
import com.jaime.parkingGarage.model.entity.ReservationStatus;
import com.jaime.parkingGarage.model.entity.User;
import com.jaime.parkingGarage.repository.BoatRepository;
import com.jaime.parkingGarage.repository.ReservationRepository;
import com.jaime.parkingGarage.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final BoatRepository boatRepository;

    public ReservationService(ReservationRepository reservationRepository,
                              UserRepository userRepository,
                              BoatRepository boatRepository) {
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
        this.boatRepository = boatRepository;
    }

    public Reservation createReservation(UUID userId, CreateReservationRequest request) {

        // 1. Check boat availability (overlapping reservations)
        boolean boatTaken = !reservationRepository
                .findByBoatIdAndStatusNotAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
                        request.getBoatId(),
                        ReservationStatus.CANCELLED,
                        request.getEndDate(),
                        request.getStartDate()
                )
                .isEmpty();

        if (boatTaken) {
            throw new RuntimeException("Boat is already reserved for selected dates");
        }

        // 2. Calculate total days (inclusive)
        int totalDays = (int) ChronoUnit.DAYS.between(
                request.getStartDate(),
                request.getEndDate()
        ) + 1;

        // 3. Get boat and calculate cost
        Boat boat = boatRepository.findById(request.getBoatId())
                .orElseThrow(() -> new RuntimeException("Boat not found"));

        double totalCost = totalDays * boat.getPricePerDay();

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
        reservation.setBoatId(request.getBoatId());
        reservation.setStartDate(request.getStartDate());
        reservation.setEndDate(request.getEndDate());
        reservation.setTotalDays(totalDays);
        reservation.setTotalCost(totalCost);
        reservation.setStatus(ReservationStatus.ACTIVE);

        // 7. Save reservation
        return reservationRepository.save(reservation);
    }
}