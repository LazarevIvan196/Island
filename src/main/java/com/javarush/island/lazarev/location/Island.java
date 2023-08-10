package com.javarush.island.lazarev.location;

import com.javarush.island.lazarev.entities.Animal;
import com.javarush.island.lazarev.entities.Nature;
import com.javarush.island.lazarev.factory.EntityFactory;
import com.javarush.island.lazarev.repository.NatureParameters;
import com.javarush.island.lazarev.repository.ProbabilityTable;
import com.javarush.island.lazarev.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Island {
    private final int rows;
    private final int cols;
    private final View view;
    private final Location[][] locations;
    private final Lock[][] locationLocks;
    private final ScheduledExecutorService simulationExecutor = Executors.newSingleThreadScheduledExecutor();
    private final ProbabilityTable probabilityTable = new ProbabilityTable();
    private final IslandStatistics statistics = new IslandStatistics();
    private final EntityFactory entityFactory;

    public Island(int rows, int cols, View view) {
        this.rows = rows;
        this.cols = cols;
        this.view = view;
        this.locations = new Location[rows][cols];
        this.entityFactory = new EntityFactory(locations, new ProbabilityTable(), this);
        initializeGrid();
        this.locationLocks = new ReentrantLock[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                locationLocks[i][j] = new ReentrantLock();
            }
        }
    }

    private void initializeGrid() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                locations[i][j] = new Location(new ArrayList<>(), probabilityTable);
            }
        }
    }

    public void populateIsland() {
        entityFactory.createPlants();
        entityFactory.createHerbivores();
        entityFactory.createPredators();
    }

    public void startSimulation(int simulationSpeed) {
        simulationExecutor.scheduleAtFixedRate(this::updateIslandState, 0, simulationSpeed, TimeUnit.SECONDS);
    }

    public void stopSimulation() {
        try {
            if (!simulationExecutor.awaitTermination(1, TimeUnit.MINUTES)) {
                simulationExecutor.shutdownNow();
            }
        } catch (InterruptedException e) {
            simulationExecutor.shutdownNow();
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                locations[i][j].shutdownExecutors();
            }
        }
    }

    public void printStatistics() {
        statistics.collectStatistics(locations);
        statistics.printStatistics();
    }

    public void printIsland() {
        view.displayIsland(locations);
    }

    public void updateIslandState() {
        System.out.println("Updating island state...");
        printStatistics();
        moveAnimalsConcurrently();
        performEatingConcurrently();
        performReproduceConcurrently();
        printIsland();
        System.out.println("Island state updated.");
    }

    private void moveAnimalsConcurrently() {
        ExecutorService moveExecutor = Executors.newFixedThreadPool(rows * cols);
        try {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    final int currentRow = i;
                    final int currentCol = j;
                    List<Nature> entities = locations[i][j].getEntities();
                    List<Nature> entitiesCopy = new ArrayList<>(entities);
                    for (Nature entity : entitiesCopy) {
                        if (entity instanceof Animal animal) {
                            if (NatureParameters.getAnimalParameters(entity.getEntityType()).getSpeed() != 0) {
                                moveExecutor.execute(() -> moveAnimal(currentRow, currentCol, animal));
                            }
                        }
                    }
                }
            }
        } finally {
            moveExecutor.shutdown();
        }
    }

    private void moveAnimal(int currentRow, int currentCol, Animal animal) {
        locationLocks[currentRow][currentCol].lock();
        try {
            Coordinates oldCoordinates = animal.getCoordinates();
            animal.move();
            Coordinates newCoordinates = animal.getCoordinates();
            int newX = Math.max(0, Math.min(newCoordinates.x(), rows - 1));
            int newY = Math.max(0, Math.min(newCoordinates.y(), cols - 1));
            newCoordinates = new Coordinates(newX, newY);
            animal.setCurrentCoordinates(newCoordinates);
            locations[oldCoordinates.x()][oldCoordinates.y()].removeEntity(animal);
            locations[newCoordinates.x()][newCoordinates.y()].addEntity(animal);
        } finally {
            locationLocks[currentRow][currentCol].unlock();
        }
    }

    private void performEatingConcurrently() {
        ExecutorService eatExecutor = Executors.newFixedThreadPool(rows * cols);
        try {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    final int currentRow = i;
                    final int currentCol = j;
                    eatExecutor.execute(() -> locations[currentRow][currentCol].performEating());
                }
            }
        } finally {
            eatExecutor.shutdown();
        }
    }

    private void performReproduceConcurrently() {
        ExecutorService reproductionExecutor = Executors.newFixedThreadPool(rows * cols);
        try {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    final int currentRow = i;
                    final int currentCol = j;
                    reproductionExecutor.execute(() -> locations[currentRow][currentCol].performReproduction());
                }
            }
        } finally {
            reproductionExecutor.shutdown();
        }
    }
}