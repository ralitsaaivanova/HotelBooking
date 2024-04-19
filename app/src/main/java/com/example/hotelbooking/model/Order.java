package com.example.hotelbooking.model;

public class Order {
    private int id;
    private int nights;
    private double price;

    public Order(int id, int nights, double price) {
        this.id = id;
        this.nights = nights;
        this.price = price;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
