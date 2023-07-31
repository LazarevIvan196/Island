package com.javarush.island.lazarev.entities;

public abstract class Nature {
    FoodType foodType;
    TileIcon tileIcon;
    private double weight;
    private double probabilityToBeEaten;

    public Nature(TileIcon tileIcon, double weight, double probabilityToBeEaten) {
        this.tileIcon = tileIcon;
        this.weight = weight;
        this.probabilityToBeEaten = probabilityToBeEaten;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getProbabilityToBeEaten() {
        return probabilityToBeEaten;
    }

    public void setProbabilityToBeEaten(double probabilityToBeEaten) {
        this.probabilityToBeEaten = probabilityToBeEaten;
    }


    public abstract void reproduction();

    public abstract void dieOrBeEaten();

    public abstract FoodType getType();
    public abstract TileIcon getIconType();
    }
