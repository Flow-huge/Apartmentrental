package com.apartmentrental.controllers;

import com.apartmentrental.models.Apartment;
import com.apartmentrental.repositories.interfaces.IApartmentRepository;

import java.util.List;

public class ApartmentController {
    private final IApartmentRepository apartmentRepository;

    public ApartmentController(IApartmentRepository apartmentRepository) {
        this.apartmentRepository = apartmentRepository;
    }

    public String addApartment(Apartment apartment) {
        boolean success = apartmentRepository.addApartment(apartment);
        return success ? "Apartment added successfully!" : "Failed to add apartment.";
    }

    public List<Apartment> viewAllApartments() {
        return apartmentRepository.getAllApartments();
    }

    public List<Apartment> searchApartments(String city, String district, double minPrice, double maxPrice, int minRooms, int maxRooms, double minRating) {
        return apartmentRepository.filterApartments(city, district, minPrice, maxPrice, minRooms, maxRooms, minRating);
    }

    public Apartment getApartmentById(int id) {
        return apartmentRepository.getApartmentById(id);
    }

    public boolean updateApartmentStatus(int apartmentId, String status, String availabilityDate) {
        return apartmentRepository.updateApartmentStatus(apartmentId, status, availabilityDate);
    }
}
