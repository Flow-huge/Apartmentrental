package com.apartmentrental.repositories;

import com.apartmentrental.data.PostgresDB;
import com.apartmentrental.models.Apartment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ApartmentRepository {

    // Получение всех квартир
    public List<Apartment> getAllApartments() {
        List<Apartment> apartments = new ArrayList<>();
        String sql = "SELECT * FROM apartments";

        try (Connection conn = PostgresDB.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                apartments.add(createApartmentFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return apartments;
    }

    // Получение доступных квартир
    public List<Apartment> getAvailableApartments() {
        List<Apartment> apartments = new ArrayList<>();
        String sql = "SELECT * FROM apartments WHERE status = 'available'";

        try (Connection conn = PostgresDB.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                apartments.add(createApartmentFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return apartments;
    }

    // Поиск квартиры по названию
    public List<Apartment> findApartmentByName(String name) {
        List<Apartment> apartments = new ArrayList<>();
        String sql = "SELECT * FROM apartments WHERE name ILIKE ?";

        try (Connection conn = PostgresDB.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "%" + name + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                apartments.add(createApartmentFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return apartments;
    }

    // Поиск квартиры по фильтрам (город, минимальная и максимальная цена)
    public List<Apartment> findApartmentsByFilters(String city, double minPrice, double maxPrice) {
        List<Apartment> apartments = new ArrayList<>();
        String sql = "SELECT * FROM apartments WHERE city = ? AND price_day BETWEEN ? AND ?";

        try (Connection conn = PostgresDB.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, city);
            pstmt.setDouble(2, minPrice);
            pstmt.setDouble(3, maxPrice);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                apartments.add(createApartmentFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return apartments;
    }

    // Исправленное условное выражение (ошибка в 60 строке)
    public void updateApartmentStatus(int apartmentId, boolean isAvailable) {
        String sql = "UPDATE apartments SET status = ? WHERE id = ?";

        try (Connection conn = PostgresDB.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, isAvailable ? "available" : "unavailable"); // Исправлено
            pstmt.setInt(2, apartmentId);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Apartment createApartmentFromResultSet(ResultSet rs) throws SQLException {
        return new Apartment(
        );
    }
}
