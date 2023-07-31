package com.javarush.island.lazarev.entities.herbivores;

import com.javarush.island.lazarev.entities.Animal;
import com.javarush.island.lazarev.entities.FoodType;
import com.javarush.island.lazarev.entities.Nature;
import com.javarush.island.lazarev.entities.TileIcon;

public class Caterpillar extends Animal {


    public Caterpillar(TileIcon tileIcon, double weight, double probabilityToBeEaten, FoodType foodType, int moveSpeed, double foodNeeded, double probabilityToFindFood) {
        super(tileIcon, weight, probabilityToBeEaten, foodType, moveSpeed, foodNeeded, probabilityToFindFood);
    }

    @Override
    public void moveUp() {

    }

    @Override
    public void moveDown() {

    }

    @Override
    public void moveLeft() {

    }

    @Override
    public void moveRight() {

    }

    @Override
    public void eat(Nature food) {

    }


    @Override
    public void reproduction() {

    }



    @Override
    public void dieOrBeEaten() {

    }

    @Override
    public FoodType getType() {
        return FoodType.MEAT;
    }

    @Override
    public TileIcon getIconType() {
        return TileIcon.CATERPILLAR;
    }
}
