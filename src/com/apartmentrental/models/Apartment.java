package com.apartmentrental.models;

public class Apartment {
    private int id;
    private String name;
    private String city;
    private String district;
    private String street;
    private int floor;
    private int rooms;
    private double priceDay;
    private double priceMonth;
    private double priceYear;
    private String status;
    private String availabilityDate;
    private double rating;

    public Apartment(int id, String name, String city, String district, String street, int floor, int rooms,
                     double priceDay, double priceMonth, double priceYear, String status, String availabilityDate, double rating) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.district = district;
        this.street = street;
        this.floor = floor;
        this.rooms = rooms;
        this.priceDay = priceDay;
        this.priceMonth = priceMonth;
        this.priceYear = priceYear;
        this.status = status;
        this.availabilityDate = availabilityDate;
        this.rating = rating;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getCity() { return city; }
    public String getDistrict() { return district; }
    public String getStreet() { return street; }
    public int getFloor() { return floor; }
    public int getRooms() { return rooms; }
    public double getPriceDay() { return priceDay; }
    public double getPriceMonth() { return priceMonth; }
    public double getPriceYear() { return priceYear; }
    public String getStatus() { return status; }
    public String getAvailabilityDate() { return availabilityDate; }
    public double getRating() { return rating; }
}
