package com.example.hotelbooking.model;

public class Hotel {
    private int id;
    private String name;
    private String type_of_room;
    private String price;

    public Hotel(int id, String name, String type_of_room, String price) {
        this.id = id;
        this.name = name;
        this.type_of_room = type_of_room;
        this.price = price;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
