package com.example.hotelbooking.model;

import java.sql.Blob;

public class Hotel {
    private int id;
    private String name;
    private String type_of_room;
    private String location;
    private double price;

    private byte[] img;

    public Hotel(int id, String name, String type_of_room,String location, double price,byte[]img) {
        this.id = id;
        this.name = name;
        this.type_of_room = type_of_room;
        this.location=location;
        this.price = price;
        this.img = img;
    }

    public Hotel() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType_of_room() {
        return type_of_room;
    }

    public void setType_of_room(String type_of_room) {
        this.type_of_room = type_of_room;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }
}
