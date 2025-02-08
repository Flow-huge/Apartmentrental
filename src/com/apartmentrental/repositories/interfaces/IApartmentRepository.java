package com.apartmentrental.repositories.interfaces;

import com.apartmentrental.models.Apartment;
import java.util.List;

public interface IApartmentRepository {
    List<Apartment> getAllApartments();
    List<Apartment> findApartmentByName(String name);
}
