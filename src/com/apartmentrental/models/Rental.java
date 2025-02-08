package com.apartmentrental.models;

public class Rental {
    private int id;
    private int userId;
    private int apartmentId;
    private String startDate;
    private String endDate;

    public Rental(int id, int userId, int apartmentId, String startDate, String endDate, String status) {}

    public Rental(int userId, int apartmentId, String startDate, String endDate) {
        setUserId(userId);
        setApartmentId(apartmentId);
        setStartDate(startDate);
        setEndDate(endDate);
    }

    public Rental(int id, int userId, int apartmentId, String startDate, String endDate) {
        this(userId, apartmentId, startDate, endDate);
        setId(id);
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getApartmentId() { return apartmentId; }
    public void setApartmentId(int apartmentId) { this.apartmentId = apartmentId; }

    public String getStartDate() { return startDate; }
    public void setStartDate(String startDate) { this.startDate = startDate; }

    public String getEndDate() { return endDate; }
    public void setEndDate(String endDate) { this.endDate = endDate; }

    @Override
    public String toString() {
        return "Rental{" +
                "id=" + id +
                ", userId=" + userId +
                ", apartmentId=" + apartmentId +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }
}
