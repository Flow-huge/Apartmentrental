package com.apartmentrental.repositories;

import com.apartmentrental.models.Rental;
import com.apartmentrental.repositories.interfaces.IRentalRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RentalRepository implements IRentalRepository {
    private final Connection connection;

    public RentalRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean addRental(Rental rental) {
        String sql = "INSERT INTO rentals (user_id, apartment_id, start_date, duration) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, rental.getUserId());
            statement.setInt(2, rental.getApartmentId());
            statement.setDate(3, Date.valueOf(rental.getStartDate()));
            statement.setString(4, rental.getDuration());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error while adding rental: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Rental> getRentalsByUserId(int userId) {
        List<Rental> rentals = new ArrayList<>();
        String sql = "SELECT * FROM rentals WHERE user_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Rental rental = new Rental(
                        resultSet.getInt("id"),
                        resultSet.getInt("user_id"),
                        resultSet.getInt("apartment_id"),
                        resultSet.getDate("start_date").toString(),
                        resultSet.getString("duration")
                );
                rentals.add(rental);
            }
        } catch (SQLException e) {
            System.err.println("Error while retrieving rentals: " + e.getMessage());
        }
        return rentals;
    }
}
