package com.jaime.parkingGarage.repository;

import com.jaime.parkingGarage.model.entity.Reservation;
import com.jaime.parkingGarage.model.entity.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface ReservationRepository extends JpaRepository<Reservation, UUID> {

    /*
    SELECT r FROM Reservation r
    WHERE r.spotId = :spotId
    AND r.status != :status
    AND r.startDate <= :endDate
    AND r.endDate >= :startDate
     */
    List<Reservation> findBySpotIdAndStatusNotAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
            String spotId,
            ReservationStatus status,
            LocalDate endDate,
            LocalDate startDate
    );

    List<Reservation> findByUserId(UUID userId);

    List<Reservation> findByVehicleId(UUID vehicleId);

}