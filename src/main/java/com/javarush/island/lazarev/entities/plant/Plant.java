package com.javarush.island.lazarev.entities.plant;

import com.javarush.island.lazarev.entities.FoodType;
import com.javarush.island.lazarev.entities.Nature;
import com.javarush.island.lazarev.entities.TileIcon;

public abstract class Plant extends Nature {
FoodType foodType;


    public Plant(TileIcon tileIcon, double weight, double probabilityToBeEaten, FoodType foodType) {
        super(tileIcon, weight, probabilityToBeEaten);
        this.foodType = foodType;
    }

    @Override
    public FoodType getType() {
        return FoodType.PLANT;
    }


}
