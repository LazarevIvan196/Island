package com.javarush.island.lazarev.factory;

import com.javarush.island.lazarev.entities.Animal;
import com.javarush.island.lazarev.entities.FoodType;
import com.javarush.island.lazarev.entities.TileIcon;
import com.javarush.island.lazarev.entities.plant.Grass;
import com.javarush.island.lazarev.entities.plant.Plant;
import com.javarush.island.lazarev.entities.plant.Plants;
import com.javarush.island.lazarev.entities.plant.Woods;

public class PlantCreator implements Factory {
    private final Plants plantType;

    public PlantCreator(Plants plantType) {
        this.plantType = plantType;
    }


    @Override
    public Plant createPlant() {
        return switch (plantType) {
            case WOOD -> new Woods(TileIcon.WOODS,30, 2000, FoodType.PLANT);
            case GRAS -> new Grass(TileIcon.GRASS,50, 1,FoodType.PLANT);
        };
    }

    @Override
    public Animal createPredator() {
        throw new UnsupportedOperationException("PlantCreator не поддерживает создание хищников.");
    }

    @Override
    public Animal createHerbivores() {
        throw new UnsupportedOperationException("PlantCreator не поддерживает создание травоядных.");
    }
}