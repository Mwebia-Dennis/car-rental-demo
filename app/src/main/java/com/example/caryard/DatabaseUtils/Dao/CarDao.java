package com.example.caryard.DatabaseUtils.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.caryard.Models.Car;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface CarDao { //car data access object

    @Query("SELECT * FROM cars")
    List<Car> getAllCars();

    @Insert
    void insertCar(Car car);

    @Update
    void updateCar(Car car);

    @Delete
    void deleteCar(Car car);
}
