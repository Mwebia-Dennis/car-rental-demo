package com.example.caryard.Models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "cars")
public class Car implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int id; // needed for maintaining uniqueness of data in database
    public String carMake, carManYr, carColor, carRego, carPrice;

    public Car() {
        // empty constructor for initializing data as empty strings
        carMake = carManYr = carColor = carRego = carPrice = "";
    }
    public Car(String carMake, String carManYr, String carColor, String carRego, String carPrice) {
        // initializing our variables
        this.carMake = carMake;
        this.carManYr = carManYr;
        this.carColor = carColor;
        this.carRego = carRego;
        this.carPrice = carPrice;
    }

}
