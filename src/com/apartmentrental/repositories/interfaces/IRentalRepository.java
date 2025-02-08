package com.apartmentrental.repositories.interfaces;

import com.apartmentrental.models.Rental;
import java.time.LocalDate;
import java.util.List;

public interface IRentalRepository {
    void rentApartment(int userId, int apartmentId, LocalDate startDate, String durationType); // ✅ LocalDate вместо String
    List<Rental> getRentalsByUserId(int userId);
}
