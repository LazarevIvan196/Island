package com.javarush.island.lazarev.factory;

import com.javarush.island.lazarev.entities.Animal;
import com.javarush.island.lazarev.entities.FoodType;
import com.javarush.island.lazarev.entities.TileIcon;
import com.javarush.island.lazarev.entities.plant.Plant;
import com.javarush.island.lazarev.entities.predators.*;

/**
 * Фабрика для создания объектов хищников.
 */
public class PredatorsCreator implements Factory {
    private final Predators predatorsType;

    /**
     * Конструктор фабрики хищников.
     *
     * @param predatorsType тип хищника.
     */
    public PredatorsCreator(Predators predatorsType) {
        this.predatorsType = predatorsType;
    }

    /**
     * Создает экземпляр хищника в зависимости от типа.
     *
     * @return объект хищника.
     * @throws IllegalArgumentException если тип хищника неизвестен.
     */
    @Override
    public Animal createPredator() {
        return switch (predatorsType) {
            case BEAR -> new Bear(TileIcon.BEAR,500, 2, FoodType.MEAT, 0, 60, 80);
            case EAGLE -> new Eagle(TileIcon.EAGLE,6, 3,FoodType.MEAT,  0, 80, 1);
            case FOX -> new Fox(TileIcon.FOX,8, 2, FoodType.MEAT,20,  60, 2);
            case TIGER -> new Tiger(TileIcon.TIGER,600, 3, FoodType.MEAT, 0, 65, 80);
            case WOLF -> new Wolf(TileIcon.WOLF,50, 3, FoodType.MEAT, 10, 60, 8);
            default -> throw new IllegalArgumentException("Unknown predators type: " + predatorsType);
        };
    }

    @Override
    public Animal createHerbivores() {
        throw new UnsupportedOperationException("PlantCreator не поддерживает создание травоядных.");
    }

    @Override
    public Plant createPlant() {
        throw new UnsupportedOperationException("HerbivoresCreator не поддерживает создание растений.");
    }
}

