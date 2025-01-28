package com.apartmentrental.controllers;

import com.apartmentrental.models.Rental;
import com.apartmentrental.models.User;
import com.apartmentrental.repositories.interfaces.IRentalRepository;

import java.util.List;

public class RentalController {
    private final IRentalRepository rentalRepository;

    public RentalController(IRentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    public String rentApartment(int userId, int apartmentId, String startDate, String duration) {
        Rental rental = new Rental(0, userId, apartmentId, startDate, duration);
        boolean success = rentalRepository.addRental(rental);
        return success ? "Apartment rented successfully!" : "Failed to rent apartment.";
    }

    public List<Rental> viewUserRentals(int userId) {
        return rentalRepository.getRentalsByUserId(userId);
    }
}
