package com.apartmentrental.controllers;

import com.apartmentrental.models.Rental;
import com.apartmentrental.repositories.RentalRepository;

import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;

public class RentalController {
    private final RentalRepository rentalRepository = new RentalRepository();

    public void rentApartment(int userId) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите ID квартиры для аренды: ");
        int apartmentId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Введите дату заезда (YYYY-MM-DD): ");
        String startDateStr = scanner.nextLine();
        LocalDate startDate = LocalDate.parse(startDateStr); //
        System.out.print("Введите срок аренды (день/месяц/год): ");
        String durationType = scanner.nextLine();

        rentalRepository.rentApartment(userId, apartmentId, startDate, durationType);
        System.out.println("Квартира успешно арендована!");
    }

    public void viewUserRentals(int userId) {
        List<Rental> rentals = rentalRepository.getRentalsByUserId(userId);
        if (rentals.isEmpty()) {
            System.out.println("Нет активных аренд.");
        } else {
            for (Rental rental : rentals) {
                System.out.println("Арендована квартира ID: " + rental.getApartmentId() +
                        " | Дата заезда: " + rental.getStartDate() + " | Дата окончания: " + rental.getEndDate());
            }
        }
    }
}
