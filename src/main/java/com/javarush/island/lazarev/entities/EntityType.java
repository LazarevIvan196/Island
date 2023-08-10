package com.javarush.island.lazarev.entities;

import java.util.Arrays;
import java.util.List;

public enum EntityType {
    // Хищники
    WOLF,
    TIGER,
    FOX,
    BEAR,
    EAGLE,

    // Травоядные
    BEAVER,
    DEER,
    RABBIT,
    MOUSE,
    HARE,
    SHEEP,
    BOAR,
    DUCK,
    CATERPILLAR,
    BEE,

    // Растения
    WOODS,
    GRASS;


    public static List<EntityType> getPlantTypes() {
        return Arrays.asList(WOODS, GRASS);
    }

    public static List<EntityType> getHerbivoreTypes() {
        return Arrays.asList(CATERPILLAR, HARE, SHEEP, BEE, MOUSE, DEER, BEAVER, RABBIT, BOAR, DUCK);
    }

    public static List<EntityType> getPredatorTypes() {
        return Arrays.asList(BEAR, TIGER, WOLF, FOX, EAGLE);
    }
}