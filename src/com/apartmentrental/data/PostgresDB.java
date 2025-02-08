package com.apartmentrental.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresDB {
    private static PostgresDB instance;
    private Connection connection;

    private static final String URL = "jdbc:postgresql://localhost:5432/RentApartments";
    private static final String USER = "postgres";
    private static final String PASSWORD = "ваш_пароль";

    private PostgresDB() {
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Успешное подключение к БД!");
        } catch (ClassNotFoundException e) {
            System.out.println("❌ Драйвер PostgreSQL не найден!");
        } catch (SQLException e) {
            System.out.println("❌ Ошибка подключения к БД: " + e.getMessage());
        }
    }

    public static synchronized PostgresDB getInstance() {
        if (instance == null) {
            instance = new PostgresDB();
        }
        return instance;
    }

    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("🔄 Переподключение к БД...");
            }
        } catch (SQLException e) {
            System.out.println("❌ Ошибка переподключения: " + e.getMessage());
        }
        return connection;
    }
}
