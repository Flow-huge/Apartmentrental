package com.apartmentrental.repositories.interfaces;

import com.apartmentrental.models.Rental;

import java.util.List;

public interface IRentalRepository {
    boolean addRental(Rental rental);

    List<Rental> getRentalsByUserId(int userId);
}
