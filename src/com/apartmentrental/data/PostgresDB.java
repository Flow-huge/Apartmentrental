package com.apartmentrental.data;

import com.apartmentrental.data.interfaces.IDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresDB implements IDB {
    private static final String URL = "jdbc:postgresql://localhost:5432/apartments_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "TaMiR1234456"; // Замените на ваш пароль

    @Override
    public Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Failed to connect to the database: " + e.getMessage());
            return null;
        }
    }
}
