package com.example.caryard.DatabaseUtils.AppDatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.caryard.DatabaseUtils.Dao.CarDao;
import com.example.caryard.Models.Car;

@Database(entities = {Car.class}, version = 1)

// Our database class for managing and migrating app database

public abstract class AppDatabase extends RoomDatabase {

    // create an instance of the database
    public static AppDatabase getInstance(Context context) {
        return Room.databaseBuilder(context,
                AppDatabase.class, "caryard-database").build();
    }

    public abstract CarDao carDao(); // data access object
}

