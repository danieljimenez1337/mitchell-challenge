package com.example.challenge.model;

import javax.persistence.Entity;

@Entity
public class Vehicle extends BaseEntity {

    public int year;
    public String make;
    public String model;

    public Vehicle() {
    }

    public Vehicle(int year, String make, String model) {
        this.year = year;
        this.make = make;
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
