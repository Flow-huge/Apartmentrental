package com.apartmentrental.repositories;

import com.apartmentrental.data.PostgresDB;
import com.apartmentrental.models.Rental;
import com.apartmentrental.repositories.interfaces.IRentalRepository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class RentalRepository implements IRentalRepository {
    private final PostgresDB postgresDB = PostgresDB.getInstance();
    private static final Logger LOGGER = Logger.getLogger(RentalRepository.class.getName());

    @Override
    public List<Rental> getRentalsByUserId(int userId) {
        return getUserRentalsWithDetails(userId);
    }

    // ✅ Получение аренд с информацией о квартирах (SQL JOIN)
    public List<Rental> getUserRentalsWithDetails(int userId) {
        List<Rental> rentals = new ArrayList<>();
        String sql = """
            SELECT r.id, r.start_date, r.end_date, 
                   a.name, a.city, a.price_day, a.price_month, a.price_year 
            FROM rentals r
            JOIN apartments a ON r.apartment_id = a.id
            WHERE r.user_id = ?;
        """;

        try (Connection conn = postgresDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                rentals.add(new Rental(
                        rs.getInt("id"),
                        userId,
                        rs.getString("name"),
                        rs.getString("city"),
                        rs.getDouble("price_day"),
                        rs.getDouble("price_month"),
                        rs.getDouble("price_year"),
                        rs.getString("start_date"),
                        rs.getString("end_date")
                ));
            }
        } catch (SQLException e) {
            LOGGER.severe("Ошибка при получении аренд: " + e.getMessage());
        }
        return rentals;
    }

    // ✅ Метод для вывода арендованных квартир
    public void viewUserRentalsWithDetails(int userId) {
        List<Rental> rentals = getUserRentalsWithDetails(userId);

        if (rentals.isEmpty()) {
            System.out.println("🏠 У вас нет активных арендованных квартир.");
        } else {
            System.out.println("\n🏠 Ваши арендованные квартиры:");
            for (Rental rental : rentals) {
                System.out.println(rental);
            }
        }
    }
    private double getRentalCost(int apartmentId, String durationType, Connection conn) throws SQLException {
        String column = switch (durationType.toLowerCase()) {
            case "день" -> "price_day";
            case "месяц" -> "price_month";
            case "год" -> "price_year";
            default -> throw new IllegalArgumentException("Неверный тип аренды");
        };

        String sql = "SELECT " + column + " FROM apartments WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, apartmentId);
            ResultSet rs = stmt.executeQuery();
            return rs.next() ? rs.getDouble(column) : 0.0;
        }
    }


    // ✅ Метод аренды квартиры (включает списание денег и изменение статуса квартиры)
    @Override
    public void rentApartment(int userId, int apartmentId, LocalDate startDate, String durationType) {
        try (Connection conn = postgresDB.getConnection()) {
            conn.setAutoCommit(false);

            double walletBalance = getUserBalance(userId, conn);
            double rentalCost = getRentalCost(apartmentId, durationType, conn);

            if (walletBalance < rentalCost) {
                System.out.println("❌ Недостаточно средств для аренды.");
                return;
            }

            LocalDate endDate = calculateEndDate(startDate, durationType);
            updateUserBalance(userId, walletBalance - rentalCost, conn);
            insertRental(userId, apartmentId, startDate, endDate, conn);
            updateApartmentStatus(apartmentId, "rented", conn);

            conn.commit();
            System.out.println("✅ Квартира успешно арендована!");
        } catch (SQLException e) {
            LOGGER.severe("Ошибка при аренде квартиры: " + e.getMessage());
        }
    }

    // ✅ Подсчет даты окончания аренды
    private LocalDate calculateEndDate(LocalDate startDate, String durationType) {
        return switch (durationType.toLowerCase()) {
            case "день" -> startDate.plusDays(1);
            case "месяц" -> startDate.plusMonths(1);
            case "год" -> startDate.plusYears(1);
            default -> throw new IllegalArgumentException("Неверный срок аренды");
        };
    }

    private double getUserBalance(int userId, Connection conn) throws SQLException {
        String sql = "SELECT wallet_balance FROM users WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            return rs.next() ? rs.getDouble("wallet_balance") : 0.0;
        }
    }

    private void updateUserBalance(int userId, double newBalance, Connection conn) throws SQLException {
        String sql = "UPDATE users SET wallet_balance = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, newBalance);
            stmt.setInt(2, userId);
            stmt.executeUpdate();
        }
    }

    private void insertRental(int userId, int apartmentId, LocalDate startDate, LocalDate endDate, Connection conn) throws SQLException {
        String sql = "INSERT INTO rentals (user_id, apartment_id, start_date, end_date, status) VALUES (?, ?, ?, ?, 'active')";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, apartmentId);
            stmt.setDate(3, Date.valueOf(startDate));
            stmt.setDate(4, Date.valueOf(endDate));
            stmt.executeUpdate();
        }
    }

    private void updateApartmentStatus(int apartmentId, String status, Connection conn) throws SQLException {
        String sql = "UPDATE apartments SET status = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, status);
            stmt.setInt(2, apartmentId);
            stmt.executeUpdate();
        }
    }
}
