package com.javarush.island.lazarev.factory;
import com.javarush.island.lazarev.entities.Animal;
import com.javarush.island.lazarev.entities.plant.Plant;


public interface Factory {
    Animal createPredator();
    Animal createHerbivores();
    Plant createPlant();
}
