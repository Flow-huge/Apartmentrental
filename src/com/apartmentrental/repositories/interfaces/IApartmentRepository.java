package com.apartmentrental.repositories.interfaces;

import com.apartmentrental.models.Apartment;

import java.util.List;

public interface IApartmentRepository {
    boolean addApartment(Apartment apartment);
    Apartment getApartmentById(int id);
    List<Apartment> getAllApartments();
    List<Apartment> filterApartments(String city, String district, double minPrice, double maxPrice, int minRooms, int maxRooms, double minRating);
    boolean updateApartmentStatus(int apartmentId, String status, String availabilityDate);
}
