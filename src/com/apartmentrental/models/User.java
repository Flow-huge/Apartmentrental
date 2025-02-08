package com.apartmentrental.models;

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String username;
    private String phoneNumber;
    private String password;
    private double walletBalance;
    private String role; // ✅ Добавлено поле "роль"

    // ✅ Конструктор для создания пользователя с ID
    public User(int id, String firstName, String lastName, String username, String phoneNumber, String password, double walletBalance, String role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.walletBalance = walletBalance;
        this.role = role;
    }

    // ✅ Конструктор для регистрации нового пользователя (без ID)
    public User(String firstName, String lastName, String username, String phoneNumber, String password, double walletBalance, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.walletBalance = walletBalance;
        this.role = role;
    }

    // ✅ Геттеры и сеттеры
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public double getWalletBalance() { return walletBalance; }
    public void setWalletBalance(double walletBalance) { this.walletBalance = walletBalance; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    // ✅ Вывод информации о пользователе
    @Override
    public String toString() {
        return "👤 Пользователь{" +
                "ID=" + id +
                ", Имя='" + firstName + '\'' +
                ", Фамилия='" + lastName + '\'' +
                ", Никнейм='" + username + '\'' +
                ", Телефон='" + phoneNumber + '\'' +
                ", Баланс=" + walletBalance + " KZT" +
                ", Роль='" + role + '\'' +
                '}';
    }
}
