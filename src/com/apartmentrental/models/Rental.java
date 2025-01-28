package com.apartmentrental.models;

public class Rental {
    private int id;
    private int userId;
    private int apartmentId;
    private String startDate; // Дата заезда
    private String duration;  // Продолжительность аренды (например, "1 день", "1 месяц", "1 год")

    // Конструктор
    public Rental(int id, int userId, int apartmentId, String startDate, String duration) {
        this.id = id;
        this.userId = userId;
        this.apartmentId = apartmentId;
        this.startDate = startDate;
        this.duration = duration;
    }

    // Геттеры и сеттеры
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(int apartmentId) {
        this.apartmentId = apartmentId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
