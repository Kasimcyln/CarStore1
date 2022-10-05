package com.kasimkartal866.carstore.db;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Car {

    @PrimaryKey(autoGenerate = true)
    public int id;
    private String brand;
    private String model ;
    private String km ;
    private String color ;
    private String imageAddress;
    private int userId;

    public Car() {
    }

    public Car(String brand, String model, String km, String color, String imageAddress, int userId) {
        this.brand = brand;
        this.model = model;
        this.km = km;
        this.color = color;
        this.imageAddress = imageAddress;
        this.userId = userId;
    }
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getKm() {
        return km;
    }

    public void setKm(String km) {
        this.km = km;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getImageAddress() {
        return imageAddress;
    }

    public void setImageAddress(String imageAddress) {
        this.imageAddress = imageAddress;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

}
