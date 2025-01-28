package com.apartmentrental;

import com.apartmentrental.controllers.ApartmentController;
import com.apartmentrental.controllers.RentalController;
import com.apartmentrental.controllers.UserController;
import com.apartmentrental.data.PostgresDB;
import com.apartmentrental.models.Apartment;
import com.apartmentrental.models.User;
import com.apartmentrental.repositories.ApartmentRepository;
import com.apartmentrental.repositories.RentalRepository;
import com.apartmentrental.repositories.UserRepository;

import java.sql.Connection;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Connection connection = new PostgresDB().getConnection();

        UserController userController = new UserController(new UserRepository(connection));
        ApartmentController apartmentController = new ApartmentController(new ApartmentRepository(connection));
        RentalController rentalController = new RentalController(new RentalRepository(connection));

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        System.out.println("Welcome to the Apartment Rental System!");

        while (!exit) {
            System.out.println("\n1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Очистка буфера

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter first name: ");
                    String firstName = scanner.nextLine();
                    System.out.print("Enter last name: ");
                    String lastName = scanner.nextLine();
                    System.out.print("Enter phone number: ");
                    String phone = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();
                    System.out.print("Enter initial wallet balance: ");
                    double balance = scanner.nextDouble();
                    scanner.nextLine();

                    String response = userController.registerUser(firstName, lastName, phone, password, balance);
                    System.out.println(response);
                }
                case 2 -> {
                    System.out.print("Enter phone number: ");
                    String phone = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();

                    User user = userController.loginUser(phone, password);
                    if (user != null) {
                        System.out.println("Login successful! Welcome, " + user.getFirstName());

                        boolean loggedIn = true;
                        while (loggedIn) {
                            System.out.println("\n1. View all apartments");
                            System.out.println("2. Search apartments");
                            System.out.println("3. Rent an apartment");
                            System.out.println("4. View wallet");
                            System.out.println("5. Logout");
                            System.out.print("Choose an option: ");
                            int userChoice = scanner.nextInt();
                            scanner.nextLine();

                            switch (userChoice) {
                                case 1 -> {
                                    System.out.println("Available apartments:");
                                    apartmentController.viewAllApartments().forEach(System.out::println);
                                }
                                case 2 -> {
                                    System.out.print("Enter city: ");
                                    String city = scanner.nextLine();
                                    System.out.print("Enter district: ");
                                    String district = scanner.nextLine();
                                    System.out.print("Enter min price: ");
                                    double minPrice = scanner.nextDouble();
                                    System.out.print("Enter max price: ");
                                    double maxPrice = scanner.nextDouble();
                                    System.out.print("Enter min rooms: ");
                                    int minRooms = scanner.nextInt();
                                    System.out.print("Enter max rooms: ");
                                    int maxRooms = scanner.nextInt();
                                    System.out.print("Enter min rating: ");
                                    double minRating = scanner.nextDouble();
                                    scanner.nextLine();

                                    apartmentController.searchApartments(city, district, minPrice, maxPrice, minRooms, maxRooms, minRating).forEach(System.out::println);
                                }
                                case 3 -> {
                                    System.out.print("Enter apartment ID to rent: ");
                                    int apartmentId = scanner.nextInt();
                                    scanner.nextLine();
                                    System.out.print("Enter start date (YYYY-MM-DD): ");
                                    String startDate = scanner.nextLine();
                                    System.out.print("Enter duration (day/month/year): ");
                                    String duration = scanner.nextLine();

                                    String response = rentalController.rentApartment(user.getId(), apartmentId, startDate, duration);
                                    System.out.println(response);
                                }
                                case 4 -> {
                                    double balance = userController.getWalletBalance(user.getId());
                                    System.out.println("Your wallet balance: $" + balance);
                                    System.out.print("Do you want to add money to your wallet? (yes/no): ");
                                    String addMoney = scanner.nextLine();
                                    if (addMoney.equalsIgnoreCase("yes")) {
                                        System.out.print("Enter amount: ");
                                        double amount = scanner.nextDouble();
                                        scanner.nextLine();
                                        boolean success = userController.updateWalletBalance(user.getId(), balance + amount);
                                        System.out.println(success ? "Wallet updated successfully!" : "Failed to update wallet.");
                                    }
                                }
                                case 5 -> {
                                    loggedIn = false;
                                    System.out.println("Logged out successfully!");
                                }
                            }
                        }
                    } else {
                        System.out.println("Invalid credentials!");
                    }
                }
                case 3 -> exit = true;
            }
        }
        System.out.println("Thank you for using the Apartment Rental System!");
    }
}
