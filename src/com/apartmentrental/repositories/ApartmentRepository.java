package com.apartmentrental.repositories;

import com.apartmentrental.models.Apartment;
import com.apartmentrental.repositories.interfaces.IApartmentRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ApartmentRepository implements IApartmentRepository {
    private final Connection connection;

    public ApartmentRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean addApartment(Apartment apartment) {
        try {
            String query = "INSERT INTO apartments (name, city, district, street, floor, rooms, price_day, price_month, price_year, status, availability_date, rating) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, apartment.getId());
            statement.setString(2, apartment.getCity());
            statement.setString(3, apartment.getDistrict());
            statement.setString(4, apartment.getStreet());
            statement.setInt(5, apartment.getFloor());
            statement.setInt(6, apartment.getRooms());
            statement.setDouble(7, apartment.getPriceDay());
            statement.setDouble(8, apartment.getPriceMonth());
            statement.setDouble(9, apartment.getPriceYear());
            statement.setString(10, apartment.getStatus());
            statement.setString(11, apartment.getAvailabilityDate());
            statement.setDouble(12, apartment.getRating());
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Apartment getApartmentById(int id) {
        try {
            String query = "SELECT * FROM apartments WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Apartment(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("city"),
                        resultSet.getString("district"),
                        resultSet.getString("street"),
                        resultSet.getInt("floor"),
                        resultSet.getInt("rooms"),
                        resultSet.getDouble("price_day"),
                        resultSet.getDouble("price_month"),
                        resultSet.getDouble("price_year"),
                        resultSet.getString("status"),
                        resultSet.getString("availability_date"),
                        resultSet.getDouble("rating")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Apartment> getAllApartments() {
        List<Apartment> apartments = new ArrayList<>();
        try {
            String query = "SELECT * FROM apartments";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                apartments.add(new Apartment(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("city"),
                        resultSet.getString("district"),
                        resultSet.getString("street"),
                        resultSet.getInt("floor"),
                        resultSet.getInt("rooms"),
                        resultSet.getDouble("price_day"),
                        resultSet.getDouble("price_month"),
                        resultSet.getDouble("price_year"),
                        resultSet.getString("status"),
                        resultSet.getString("availability_date"),
                        resultSet.getDouble("rating")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return apartments;
    }

    @Override
    public List<Apartment> filterApartments(String city, String district, double minPrice, double maxPrice, int minRooms, int maxRooms, double minRating) {
        List<Apartment> apartments = new ArrayList<>();
        try {
            String query = "SELECT * FROM apartments WHERE city LIKE ? AND district LIKE ? AND price_day >= ? AND price_day <= ? AND rooms >= ? AND rooms <= ? AND rating >= ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, city.isEmpty() ? "%" : city);
            statement.setString(2, district.isEmpty() ? "%" : district);
            statement.setDouble(3, minPrice);
            statement.setDouble(4, maxPrice);
            statement.setInt(5, minRooms);
            statement.setInt(6, maxRooms);
            statement.setDouble(7, minRating);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                apartments.add(new Apartment(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("city"),
                        resultSet.getString("district"),
                        resultSet.getString("street"),
                        resultSet.getInt("floor"),
                        resultSet.getInt("rooms"),
                        resultSet.getDouble("price_day"),
                        resultSet.getDouble("price_month"),
                        resultSet.getDouble("price_year"),
                        resultSet.getString("status"),
                        resultSet.getString("availability_date"),
                        resultSet.getDouble("rating")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return apartments;
    }

    @Override
    public boolean updateApartmentStatus(int apartmentId, String status, String availabilityDate) {
        try {
            String query = "UPDATE apartments SET status = ?, availability_date = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, status);
            statement.setString(2, availabilityDate);
            statement.setInt(3, apartmentId);
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
