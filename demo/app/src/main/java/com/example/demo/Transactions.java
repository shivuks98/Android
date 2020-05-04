package com.example.demo;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Transactions")
public class Transactions {


    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int Id;
    private Long PhoneNumber;
    private String Email;
    private String Description;
    private Double Amount;



    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public Long getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Double getAmount() {
        return Amount;
    }

    public void setAmount(Double amount) {
        Amount = amount;
    }
}
