package com.apartmentrental.models;

public class Apartment {
    private int id;
    private String name;
    private String city;
    private String district;
    private String street;
    private int floor;
    private int rooms;
    private double pricePerDay;
    private double pricePerMonth;
    private double pricePerYear;
    private String status;
    private String availabilityDate;
    private double rating;

    public Apartment() {}

    public Apartment(String name, String city, String district, String street, int floor, int rooms, double pricePerDay, double pricePerMonth, double pricePerYear, String status, String availabilityDate, double rating) {
        setName(name);
        setCity(city);
        setDistrict(district);
        setStreet(street);
        setFloor(floor);
        setRooms(rooms);
        setPricePerDay(pricePerDay);
        setPricePerMonth(pricePerMonth);
        setPricePerYear(pricePerYear);
        setStatus(status);
        setAvailabilityDate(availabilityDate);
        setRating(rating);
    }

    public Apartment(int id, String name, String city, String district, String street, int floor, int rooms, double pricePerDay, double pricePerMonth, double pricePerYear, String status, String availabilityDate, double rating) {
        this(name, city, district, street, floor, rooms, pricePerDay, pricePerMonth, pricePerYear, status, availabilityDate, rating);
        setId(id);
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getDistrict() { return district; }
    public void setDistrict(String district) { this.district = district; }

    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }

    public int getFloor() { return floor; }
    public void setFloor(int floor) { this.floor = floor; }

    public int getRooms() { return rooms; }
    public void setRooms(int rooms) { this.rooms = rooms; }

    public double getPricePerDay() { return pricePerDay; }
    public void setPricePerDay(double pricePerDay) { this.pricePerDay = pricePerDay; }

    public double getPricePerMonth() { return pricePerMonth; }
    public void setPricePerMonth(double pricePerMonth) { this.pricePerMonth = pricePerMonth; }

    public double getPricePerYear() { return pricePerYear; }
    public void setPricePerYear(double pricePerYear) { this.pricePerYear = pricePerYear; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getAvailabilityDate() { return availabilityDate; }
    public void setAvailabilityDate(String availabilityDate) { this.availabilityDate = availabilityDate; }

    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }

    @Override
    public String toString() {
        return "Apartment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", street='" + street + '\'' +
                ", floor=" + floor +
                ", rooms=" + rooms +
                ", pricePerDay=" + pricePerDay +
                ", pricePerMonth=" + pricePerMonth +
                ", pricePerYear=" + pricePerYear +
                ", status='" + status + '\'' +
                ", availabilityDate='" + availabilityDate + '\'' +
                ", rating=" + rating +
                '}';
    }
}
