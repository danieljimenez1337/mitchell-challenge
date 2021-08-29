package com.example.challenge.model;

import javax.persistence.Entity;

@Entity
public class Vehicle extends BaseEntity {

    public int Year;
    public String Make;
    public String Model;

    public Vehicle() {
    }

    public Vehicle(int year, String make, String model) {
        Year = year;
        Make = make;
        Model = model;
    }

    public int getYear() {
        return Year;
    }

    public void setYear(int year) {
        Year = year;
    }

    public String getMake() {
        return Make;
    }

    public void setMake(String make) {
        Make = make;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }
}
