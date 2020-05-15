package com.example.demo;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MyDoa {
    @Insert
    public void insert(StoreData data);

    @Query("select * from Users")
    public List<StoreData> getUsers ();
    @Query("select * from Users where mail=(:name)")
    public List<StoreData> getUsers (String name);

    @Query("select wallet from Users where mail=(:name)" )
    public Double getWalletAmount(String name);

    @Query("update Users set wallet=(wallet+:amount)where mail=(:mail)")
    public void addMoney(Double amount,String mail);

    @Query("update Users set wallet=(wallet+:amount) where phone=(:phone)")
    public void sendMoney(Long phone,Double amount);
    @Query("update Users set wallet=(wallet-:amount) where mail=(:mail)")
    public void debitMoney(String mail,Double amount);

    @Query("select password from Users where mail=(:mail)")
    public String getPassword(String mail);
    @Query("update Users set password=(:password) where mail=(:mail)")
    public void updatePassword(String password,String mail);

//    already registered
    @Query("select exists (select mail from Users where mail=(:mail))")
    public boolean hasMail(String mail);

//    has phone number
    @Query("select exists (select phone from Users where phone=(:phone))")
    public boolean hasPhone(Long phone);

//    Transactions DataBase
    @Insert
    public void addTransaction(Transactions data);

    @Query("select * from Transactions where PhoneNumber=(:phone)")
    public List<Transactions> getTransactions(Long phone);

//    Address
    @Insert
    public void addAddress(Address address);
    @Query("select exists (select mail from Address where mail=(:mail))")
    public boolean hasAddress(String mail);
    @Query("select * from Address where Mail=(:mail)")
    public List<Address> getAddress(String mail);
    @Update
    public void updateAddress(Address address);

    @Insert
    public void addPhoto(UserPicture userPicture);
    @Update
    public void updatePhoto(UserPicture userPicture);

    @Query("select exists (select * from UserPicture where Email=:mail)")
    public boolean hasPhoto(String mail);
    @Query("select * from UserPicture where Email=:mail")
    public List<UserPicture>getPhoto(String mail);

}
