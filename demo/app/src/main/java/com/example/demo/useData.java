package com.example.demo;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {StoreData.class,Transactions.class,Address.class,UserPicture.class},version = 4,exportSchema = false)
public abstract class useData extends RoomDatabase {
    public abstract MyDoa myDoa();
}
