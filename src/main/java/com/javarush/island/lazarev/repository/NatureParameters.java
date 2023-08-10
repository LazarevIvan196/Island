package com.javarush.island.lazarev.repository;

import com.javarush.island.lazarev.entities.EntityType;
import java.util.HashMap;
import java.util.Map;


public class NatureParameters {
    public static final double WOODS_WEIGHT = 2000;
    public static final int WOODS_MAX_ENTITIES_PER_CELL = 20;
    public static final int WOODS_SPEED = 0;
    public static final double WOODS_FOOD_NEEDED = 1000;


    public static final double GRASS_WEIGHT = 1;
    public static final int GRASS_MAX_ENTITIES_PER_CELL = 200;
    public static final int GRASS_SPEED = 0;
    public static final double GRASS_FOOD_NEEDED = 1000;


    public static final double EAGLE_WEIGHT = 6;
    public static final int EAGLE_MAX_ENTITIES_PER_CELL = 20;
    public static final int EAGLE_SPEED = 3;
    public static final double EAGLE_FOOD_NEEDED = 1;

    public static final double FOX_WEIGHT = 8;
    public static final int FOX_MAX_ENTITIES_PER_CELL = 30;
    public static final int FOX_SPEED = 2;
    public static final double FOX_FOOD_NEEDED = 2;

    public static final double TIGER_WEIGHT = 250;
    public static final int TIGER_MAX_ENTITIES_PER_CELL = 6;
    public static final int TIGER_SPEED = 3;
    public static final double TIGER_FOOD_NEEDED = 60;

    public static final double WOLF_WEIGHT = 50;
    public static final int WOLF_MAX_ENTITIES_PER_CELL = 30;
    public static final int WOLF_SPEED = 3;
    public static final double WOLF_FOOD_NEEDED = 8;

    public static final double BEAR_WEIGHT = 500;
    public static final int BEAR_MAX_ENTITIES_PER_CELL = 5;
    public static final int BEAR_SPEED = 2;
    public static final double BEAR_FOOD_NEEDED = 80;


    public static final double CATERPILLAR_WEIGHT = 0.1;
    public static final int CATERPILLAR_MAX_ENTITIES_PER_CELL = 1000;
    public static final int CATERPILLAR_SPEED = 0;
    public static final double CATERPILLAR_FOOD_NEEDED = 0.02;
    public static final double BEE_WEIGHT = 0.01;
    public static final int BEE_MAX_ENTITIES_PER_CELL = 500;
    public static final int BEE_SPEED = 3;
    public static final double BEE_FOOD_NEEDED = 0.02;

    public static final double BOAR_WEIGHT = 400;
    public static final int BOAR_MAX_ENTITIES_PER_CELL = 50;
    public static final int BOAR_SPEED = 2;
    public static final double BOAR_FOOD_NEEDED = 50;

    public static final double DEER_WEIGHT = 300;
    public static final int DEER_MAX_ENTITIES_PER_CELL = 20;
    public static final int DEER_SPEED = 4;
    public static final double DEER_FOOD_NEEDED = 50;

    public static final double DUCK_WEIGHT = 1;
    public static final int DUCK_MAX_ENTITIES_PER_CELL = 200;
    public static final int DUCK_SPEED = 4;
    public static final double DUCK_FOOD_NEEDED = 0.15;

    public static final double HARE_WEIGHT = 7;
    public static final int HARE_MAX_ENTITIES_PER_CELL = 100;
    public static final int HARE_SPEED = 3;
    public static final double HARE_FOOD_NEEDED = 1;

    public static final double MOUSE_WEIGHT = 0.05;
    public static final int MOUSE_MAX_ENTITIES_PER_CELL = 500;
    public static final int MOUSE_SPEED = 1;
    public static final double MOUSE_FOOD_NEEDED = 00.1;

    public static final double SHEEP_WEIGHT = 70;
    public static final int SHEEP_MAX_ENTITIES_PER_CELL = 140;
    public static final int SHEEP_SPEED = 3;
    public static final double SHEEP_FOOD_NEEDED = 15;

    public static final double RABBIT_WEIGHT = 2;
    public static final int RABBIT_MAX_ENTITIES_PER_CELL = 150;
    public static final int RABBIT_SPEED = 2;
    public static final double RABBIT_FOOD_NEEDED = 0.45;

    public static final double BEAVER_WEIGHT = 25;
    public static final int BEAVER_MAX_ENTITIES_PER_CELL = 40;
    public static final int BEAVER_SPEED = 2;
    public static final double BEAVER_FOOD_NEEDED = 5;


    private final double weight;
    private final int maxEntitiesPerCell;
    private final double speed;
    private double foodNeeded;

    public NatureParameters(double weight, int maxEntitiesPerCell, double speed, double foodNeeded) {

        this.weight = weight;
        this.maxEntitiesPerCell = maxEntitiesPerCell;
        this.speed = speed;
        this.foodNeeded = foodNeeded;
    }

    private static final Map<EntityType, NatureParameters> entityParametersMap = new HashMap<>();

    static {
        entityParametersMap.put(EntityType.WOODS, new NatureParameters(WOODS_WEIGHT, WOODS_MAX_ENTITIES_PER_CELL, WOODS_SPEED, WOODS_FOOD_NEEDED));
        entityParametersMap.put(EntityType.GRASS, new NatureParameters(GRASS_WEIGHT, GRASS_MAX_ENTITIES_PER_CELL, GRASS_SPEED, GRASS_FOOD_NEEDED));

        entityParametersMap.put(EntityType.BEAR, new NatureParameters(BEAR_WEIGHT, BEAR_MAX_ENTITIES_PER_CELL, BEAR_SPEED, BEAR_FOOD_NEEDED));
        entityParametersMap.put(EntityType.TIGER, new NatureParameters(TIGER_WEIGHT, TIGER_MAX_ENTITIES_PER_CELL, TIGER_SPEED, TIGER_FOOD_NEEDED));
        entityParametersMap.put(EntityType.WOLF, new NatureParameters(WOLF_WEIGHT, WOLF_MAX_ENTITIES_PER_CELL, WOLF_SPEED, WOLF_FOOD_NEEDED));
        entityParametersMap.put(EntityType.FOX, new NatureParameters(FOX_WEIGHT, FOX_MAX_ENTITIES_PER_CELL, FOX_SPEED, FOX_FOOD_NEEDED));
        entityParametersMap.put(EntityType.EAGLE, new NatureParameters(EAGLE_WEIGHT, EAGLE_MAX_ENTITIES_PER_CELL, EAGLE_SPEED, EAGLE_FOOD_NEEDED));

        entityParametersMap.put(EntityType.CATERPILLAR, new NatureParameters(CATERPILLAR_WEIGHT, CATERPILLAR_MAX_ENTITIES_PER_CELL, CATERPILLAR_SPEED, CATERPILLAR_FOOD_NEEDED));
        entityParametersMap.put(EntityType.HARE, new NatureParameters(HARE_WEIGHT, HARE_MAX_ENTITIES_PER_CELL, HARE_SPEED, HARE_FOOD_NEEDED));
        entityParametersMap.put(EntityType.SHEEP, new NatureParameters(SHEEP_WEIGHT, SHEEP_MAX_ENTITIES_PER_CELL, SHEEP_SPEED, SHEEP_FOOD_NEEDED));
        entityParametersMap.put(EntityType.BEE, new NatureParameters(BEE_WEIGHT, BEE_MAX_ENTITIES_PER_CELL, BEE_SPEED, BEE_FOOD_NEEDED));
        entityParametersMap.put(EntityType.MOUSE, new NatureParameters(MOUSE_WEIGHT, MOUSE_MAX_ENTITIES_PER_CELL, MOUSE_SPEED, MOUSE_FOOD_NEEDED));
        entityParametersMap.put(EntityType.DEER, new NatureParameters(DEER_WEIGHT, DEER_MAX_ENTITIES_PER_CELL, DEER_SPEED, DEER_FOOD_NEEDED));
        entityParametersMap.put(EntityType.BEAVER, new NatureParameters(BEAVER_WEIGHT, BEAVER_MAX_ENTITIES_PER_CELL, BEAVER_SPEED, BEAVER_FOOD_NEEDED));
        entityParametersMap.put(EntityType.RABBIT, new NatureParameters(RABBIT_WEIGHT, RABBIT_MAX_ENTITIES_PER_CELL, RABBIT_SPEED, RABBIT_FOOD_NEEDED));
        entityParametersMap.put(EntityType.BOAR, new NatureParameters(BOAR_WEIGHT, BOAR_MAX_ENTITIES_PER_CELL, BOAR_SPEED, BOAR_FOOD_NEEDED));
        entityParametersMap.put(EntityType.DUCK, new NatureParameters(DUCK_WEIGHT, DUCK_MAX_ENTITIES_PER_CELL, DUCK_SPEED, DUCK_FOOD_NEEDED));
    }

    public double getWeight() {
        return weight;
    }

    public int getMaxEntitiesPerCell() {
        return maxEntitiesPerCell;
    }

    public int getSpeed() {
        return (int) speed;
    }

    public static NatureParameters getAnimalParameters(EntityType entityType) {
        NatureParameters parameters = entityParametersMap.get(entityType);
        if (parameters == null) {
            throw new IllegalArgumentException("Неизвестный тип сущности: " + entityType);
        }
        return parameters;
    }


    public double getFoodNeeded() {
        return foodNeeded;
    }

    public synchronized void restoreFoodNeeded(EntityType entityType) {
        foodNeeded = entityParametersMap.get(entityType).foodNeeded;
    }

    public synchronized void increaseFoodNeeded(EntityType entityType, double foodWeight) {
        double originalFoodNeeded = entityParametersMap.get(entityType).foodNeeded;
        foodNeeded = Math.max(originalFoodNeeded, foodNeeded + foodWeight);
    }
    public void setFoodNeeded(double newFoodNeeded) {
        foodNeeded = newFoodNeeded;
    }
}