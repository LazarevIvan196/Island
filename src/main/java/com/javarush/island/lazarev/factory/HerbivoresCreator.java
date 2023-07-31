package com.javarush.island.lazarev.factory;
import com.javarush.island.lazarev.entities.Animal;
import com.javarush.island.lazarev.entities.FoodType;
import com.javarush.island.lazarev.entities.TileIcon;
import com.javarush.island.lazarev.entities.herbivores.*;
import com.javarush.island.lazarev.entities.plant.Plant;


public class HerbivoresCreator implements Factory {
    private final Herbivores herbivoresType;

    public HerbivoresCreator(Herbivores herbivoresType) {
        this.herbivoresType = herbivoresType;
    }


    @Override
    public Animal createHerbivores() {
        return switch (herbivoresType) {
            case BEE -> new Bee(TileIcon.BEE,0.01, 5, FoodType.MEAT,20, 0, 0 );
            case BOAR -> new Boar(TileIcon.BOAR,400, 2, FoodType.MEAT,50, 70, 50);
            case DEER -> new Deer(TileIcon.DEER,300, 4, FoodType.MEAT,15, 70, 50);
            case DUCK -> new Duck(TileIcon.DUCK,1, 4, FoodType.MEAT,10, 90, 0.15);
            case HARE -> new Hare(TileIcon.HARE,7, 3, FoodType.MEAT,50, 80, 2);
            case MOUSE -> new Mouse(TileIcon.MOUSE,0.05, 1, FoodType.MEAT,60, 70, 0.01);
            case SHEEP -> new Sheep(TileIcon.SHEEP,70, 3, FoodType.MEAT,40, 100, 30);
            case RABBIT -> new Rabbit(TileIcon.RABBIT,2, 2, FoodType.MEAT,50, 80, 0.45);
            case BEAVER -> new Beaver(TileIcon.BEAVER,2, 2, FoodType.MEAT,40, 90, 0.45);
            case CATERPILLAR -> new Caterpillar(TileIcon.CATERPILLAR,0.1, 0, FoodType.MEAT,60, 0, 0);
        };
    }

    @Override
    public Animal createPredator() {
        throw new UnsupportedOperationException("HerbivoresCreator не поддерживает создание хищников.");
    }

    @Override
    public Plant createPlant() {
        throw new UnsupportedOperationException("HerbivoresCreator не поддерживает создание растений.");
    }

}
