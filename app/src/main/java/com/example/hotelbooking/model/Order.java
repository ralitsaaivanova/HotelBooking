package com.example.hotelbooking.model;

import java.util.Date;

public class Order {
    private int id;
    private int nights;
    private Date arrival_date;
    private Date departure_date;
    private double price;

    public Order(int id, int nights, double price,Date arrival_date,Date departure_date) {
        this.id = id;
        this.nights = nights;
        this.price = price;
        this.arrival_date=arrival_date;
        this.departure_date=departure_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNights() {
        return nights;
    }

    public void setNights(int nights) {
        this.nights = nights;
    }

    public Date getArrival_date() {
        return arrival_date;
    }

    public void setArrival_date(Date arrival_date) {
        this.arrival_date = arrival_date;
    }

    public Date getDeparture_date() {
        return departure_date;
    }

    public void setDeparture_date(Date departure_date) {
        this.departure_date = departure_date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
