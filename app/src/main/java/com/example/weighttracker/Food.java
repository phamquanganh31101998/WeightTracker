package com.example.weighttracker;

public class Food {
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    String name = "";
    String unit = "";
    int calo = 0;

    public Food(String name, String unit, int calo) {
        this.name = name;
        this.unit = unit;
        this.calo = calo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getCalo() {
        return calo;
    }

    public void setCalo(int calo) {
        this.calo = calo;
    }

    @Override
    public String toString() {
        return (name + ": " + calo + "/" + unit);
    }
}
