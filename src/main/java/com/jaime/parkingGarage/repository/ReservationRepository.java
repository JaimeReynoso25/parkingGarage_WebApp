package com.jaime.parkingGarage.repository;

import com.jaime.parkingGarage.model.entity.Reservation;
import com.jaime.parkingGarage.model.entity.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface ReservationRepository extends JpaRepository<Reservation, UUID> {

    /*
    Find conflicting reservations for a boat within a given date range.

    SQL equivalent:
    SELECT *
    FROM reservation
    WHERE boat_id = :boatId
    AND status != :status
    AND start_date <= :endDate
    AND end_date >= :startDate;
     */
    List<Reservation> findByBoatIdAndStatusNotAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
            UUID boatId,
            ReservationStatus status,
            LocalDate endDate,
            LocalDate startDate
    );

    List<Reservation> findByUserId(UUID userId);
}