package com.apartmentrental.models;

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String password;
    private double walletBalance;

    public User(int id, String firstName, String lastName, String phoneNumber, String password, double walletBalance) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.walletBalance = walletBalance;
    }

    public int getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getPassword() { return password; }
    public double getWalletBalance() { return walletBalance; }

    public void setWalletBalance(double walletBalance) { this.walletBalance = walletBalance; }
}
