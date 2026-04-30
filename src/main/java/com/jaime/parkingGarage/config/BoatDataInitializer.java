package com.jaime.parkingGarage.config;

import com.jaime.parkingGarage.model.entity.Boat;
import com.jaime.parkingGarage.repository.BoatRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BoatDataInitializer implements CommandLineRunner {

    private final BoatRepository boatRepository;

    public BoatDataInitializer(BoatRepository boatRepository) {
        this.boatRepository = boatRepository;
    }

    @Override
    public void run(String... args) {

        if (boatRepository.count() > 0) {
            return; // prevents duplicate inserts on restart
        }

        createBoats("KAYAK", 20.0);
        createBoats("PONTOON", 50.0);
        createBoats("SPEEDBOAT", 100.0);
        createBoats("SAILBOAT", 150.0);
        createBoats("YACHT", 300.0);
    }

    private void createBoats(String type, double pricePerDay) {
        for (int i = 1; i <= 5; i++) {
            Boat boat = new Boat();
            boat.setBoatType(type);
            boat.setPricePerDay(pricePerDay);
            boatRepository.save(boat);
        }
    }
}