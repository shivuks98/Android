package com.example.demo;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Address")
public class Address {
    @NonNull
    @PrimaryKey
    private String Mail;
    private  String Area;
    private String Street;
    private String City;
    private String State;

    @NonNull
    public String getMail() {
        return Mail;
    }

    public void setMail(@NonNull String mail) {
        Mail = mail;
    }

    public String getArea() {
        return Area;
    }

    public void setArea(String area) {
        Area = area;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String street) {
        Street = street;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }
}

