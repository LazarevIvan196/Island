package com.javarush.island.lazarev.location;

import com.javarush.island.lazarev.entities.Animal;
import com.javarush.island.lazarev.entities.EntityType;
import com.javarush.island.lazarev.entities.Nature;
import com.javarush.island.lazarev.entities.herbivores.Herbivore;
import com.javarush.island.lazarev.entities.plant.Plant;
import com.javarush.island.lazarev.entities.predators.Predator;
import com.javarush.island.lazarev.repository.ProbabilityTable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Location {
    private final Lock locationLock = new ReentrantLock();
    private final Lock entitiesLock = new ReentrantLock();  // Добавленная блокировка
    private final List<Nature> entities;
    private final ProbabilityTable probabilityTable;
    private final ScheduledExecutorService predatorExecutor = Executors.newScheduledThreadPool(4);
    private final ScheduledExecutorService herbivoreExecutor = Executors.newScheduledThreadPool(4);

    public Location(List<Nature> entities, ProbabilityTable probabilityTable) {
        this.entities = entities;
        this.probabilityTable = probabilityTable;
    }

    public void addEntity(Nature entity) {
        entitiesLock.lock();  // Используем блокировку
        try {
            entities.add(entity);
        } finally {
            entitiesLock.unlock();
        }
    }

    public void removeEntity(Nature entity) {
        entitiesLock.lock();  // Используем блокировку
        try {
            entities.remove(entity);
        } finally {
            entitiesLock.unlock();
        }
    }

    public List<Nature> getEntities() {
        entitiesLock.lock();  // Используем блокировку
        try {
            return new ArrayList<>(entities);
        } finally {
            entitiesLock.unlock();
        }
    }

    public Nature findFoodOnSameCell(Nature predator) {
        Coordinates currentCoordinates = predator.getCoordinates();
        List<Nature> entitiesOnSameCell = getEntitiesAt(currentCoordinates);

        for (Nature entity : entitiesOnSameCell) {
            if (entity != predator) {
                boolean success = tryPredation(predator, entity);
                if (success) {
                    return entity;
                }
            }
        }

        return null;
    }

    public boolean tryPredation(Nature predator, Nature potentialPrey) {
        locationLock.lock();
        try {
            EntityType predatorType = predator.getEntityType();
            EntityType preyType = potentialPrey.getEntityType();
            int predationProbability = probabilityTable.getPredationProbability(predatorType, preyType);
            int randomNum = ThreadLocalRandom.current().nextInt(100);

            if (randomNum < predationProbability && predatorType != preyType) {
                removeEntity(potentialPrey);
                return true;
            }

            return false;
        } finally {
            locationLock.unlock();
        }
    }

    public void performEating() {
        List<Nature> entitiesCopy = new ArrayList<>(entities);
        for (Nature entity : entitiesCopy) {
            if (entity instanceof Animal && entity.isAlive()) {
                entity.eat();
            }
        }


        for (Nature herbivore : entitiesCopy) {
            if (herbivore instanceof Herbivore && herbivore.isAlive()) {
                herbivoreExecutor.schedule(() -> {
                    for (Nature potentialFood : entitiesCopy) {
                        if (potentialFood != herbivore && (potentialFood instanceof Plant || (potentialFood instanceof Herbivore
                                && potentialFood.isAlive()))) {
                            boolean success = tryPredation(herbivore, potentialFood);
                            if (success) {
//                                if (potentialFood instanceof Plant) {
//                                    System.out.println("Herbivore type: " + herbivore.getEntityType() +
//                                            " successfully ate plant.");
//                                } else {
//                                    System.out.println("Herbivore type: " + herbivore.getEntityType() +
//                                            " successfully ate herbivore type: " + potentialFood.getEntityType());
//                                }
                            }
                        }
                    }
                }, 1000, TimeUnit.MILLISECONDS);
            }
            for (Nature predator : entitiesCopy) {
                if (predator instanceof Predator && predator.isAlive()) {
                    predatorExecutor.schedule(() -> {
                        for (Nature potentialPrey : entitiesCopy) {
                            if (potentialPrey != predator && potentialPrey.isAlive() && (potentialPrey instanceof Herbivore || potentialPrey instanceof Predator)) {
                                boolean success = tryPredation(predator, potentialPrey);
                                if (success) {
//                                System.out.println("Predator type: " + predator.getEntityType() +
//                                        " successfully ate prey type: " + potentialPrey.getEntityType());
                                }
                            }
                        }
                    }, 1000, TimeUnit.MILLISECONDS);
                }

            }
        }

    }


    public List<Nature> getEntitiesAt(Coordinates coordinates) {
        List<Nature> entitiesAtCoordinates = new ArrayList<>();

        for (Nature entity : entities) {
            if (entity.getCoordinates().equals(coordinates)) {
                entitiesAtCoordinates.add(entity);
            }
        }

        return entitiesAtCoordinates;
    }

    public synchronized void performReproduction() {
        Set<Coordinates> reproducedCells = new HashSet<>();
        List<Nature> entitiesCopy = new ArrayList<>(entities);

        for (Nature entity : entitiesCopy) {
            if (entity instanceof Animal) {
                Coordinates entityCoordinates = entity.getCoordinates();
                if (!reproducedCells.contains(entityCoordinates)) {
                    ((Animal) entity).reproduceIfPossible(this);
                    reproducedCells.add(entityCoordinates);

                }
            }
        }
    }


    public void shutdownExecutors() {
        try {
            predatorExecutor.shutdown();
            herbivoreExecutor.shutdown();
            predatorExecutor.awaitTermination(1, TimeUnit.MINUTES);
            herbivoreExecutor.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
