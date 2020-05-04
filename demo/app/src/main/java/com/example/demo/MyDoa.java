package com.example.demo;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

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

//    Transactions DataBase
    @Insert
    public void addTransaction(Transactions data);

    @Query("select * from Transactions")
    public List<Transactions> getTransactions();
}
