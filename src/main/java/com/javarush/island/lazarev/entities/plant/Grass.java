package com.javarush.island.lazarev.entities.plant;


import com.javarush.island.lazarev.entities.FoodType;
import com.javarush.island.lazarev.entities.TileIcon;

public class Grass extends Plant {


    public Grass(TileIcon tileIcon, double weight, double probabilityToBeEaten, FoodType foodType) {
        super(tileIcon, weight, probabilityToBeEaten, foodType);
    }

    @Override
    public void reproduction() {

    }

    @Override
    public void dieOrBeEaten() {

    }

    @Override
    public TileIcon getIconType() {
        return TileIcon.GRASS;
    }
}
