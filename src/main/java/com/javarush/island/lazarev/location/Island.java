package com.javarush.island.lazarev.location;

import com.javarush.island.lazarev.entities.Animal;
import com.javarush.island.lazarev.entities.Nature;
import com.javarush.island.lazarev.entities.herbivores.Herbivores;
import com.javarush.island.lazarev.entities.plant.Plant;
import com.javarush.island.lazarev.entities.plant.Plants;
import com.javarush.island.lazarev.entities.predators.Predators;
import com.javarush.island.lazarev.factory.Factory;
import com.javarush.island.lazarev.factory.HerbivoresCreator;
import com.javarush.island.lazarev.factory.PlantCreator;
import com.javarush.island.lazarev.factory.PredatorsCreator;
import com.javarush.island.lazarev.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Island {
    private final int rows;
    private final int cols;
    private final View view;
    private final List<Nature>[][] grid;
    private final Location location;

    public Island(int rows, int cols, View view, Location location) {
        this.rows = rows;
        this.cols = cols;
        this.view = view;
        this.grid = new List[rows][cols];
        this.location = location; // Сохраняем ссылку на объект Location
        location.updateGrid(grid); // Обновляем сетку острова в Location
        initializeGrid();
    }
    private void initializeGrid() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = new ArrayList<>();
            }
        }
    }

    public void populateIsland() {
        createPredators();
        createHerbivores();
        createPlants();
        location.updateGrid(grid);
        printStatistics();
        printIsland();
    }

    private void createPredators() {
        for (Predators predatorType : Predators.values()) {
            int numPredators = getRandomInRange(predatorType.getMinCount(), predatorType.getMaxCount());
            for (int i = 0; i < numPredators; i++) {
                Factory predatorFactory = new PredatorsCreator(predatorType);
                Animal predator = predatorFactory.createPredator();
                placeEntity(predator);
            }
        }
    }

    private void createHerbivores() {
        for (Herbivores herbivoreType : Herbivores.values()) {
            int numHerbivores = getRandomInRange(herbivoreType.getMinCount(), herbivoreType.getMaxCount());
            for (int i = 0; i < numHerbivores; i++) {
                Factory herbivoresFactory = new HerbivoresCreator(herbivoreType);
                Animal herbivore = herbivoresFactory.createHerbivores();
                placeEntity(herbivore);
            }
        }
    }

    private void createPlants() {
        for (Plants plantType : Plants.values()) {
            int numPlants = getRandomInRange(plantType.getMinCount(), plantType.getMaxCount());
            for (int i = 0; i < numPlants; i++) {
                Factory plantFactory = new PlantCreator(plantType);
                Plant plant = plantFactory.createPlant();
                placeEntity(plant);
            }
        }
    }

    private static int getRandomInRange(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    private void placeEntity(Nature entity) {
        int row = getRandomInRange(0, rows - 1);
        int col = getRandomInRange(0, cols - 1);
        grid[row][col].add(entity);
    }

    private void printStatistics() {
        IslandStatistics statistics = new IslandStatistics();
        statistics.collectStatistics(grid);
        statistics.printStatistics();
    }

    public void printIsland() {
        view.displayIsland(getGrid());
    }

    public List<Nature>[][] getGrid() {
        return grid;
    }
}