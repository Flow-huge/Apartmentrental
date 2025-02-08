package com.apartmentrental.controllers;

import com.apartmentrental.models.Apartment;
import com.apartmentrental.repositories.ApartmentRepository;

import java.util.List;
import java.util.Scanner;

public class ApartmentController {
    private final ApartmentRepository apartmentRepository = new ApartmentRepository();
    private final Scanner scanner = new Scanner(System.in);

    public void listAllApartments() {
        List<Apartment> apartments = apartmentRepository.getAllApartments();
        apartments.forEach(System.out::println);
    }

    public void searchApartmentByName() {
        System.out.print("Введите название квартиры: ");
        String name = scanner.nextLine();
        List<Apartment> apartments = apartmentRepository.findApartmentByName(name);
        apartments.forEach(System.out::println);
    }
}
