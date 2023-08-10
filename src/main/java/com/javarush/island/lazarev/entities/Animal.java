package com.javarush.island.lazarev.entities;
import com.javarush.island.lazarev.factory.EntityFactory;
import com.javarush.island.lazarev.location.Coordinates;
import com.javarush.island.lazarev.location.Island;
import com.javarush.island.lazarev.location.Location;
import com.javarush.island.lazarev.repository.NatureParameters;
import com.javarush.island.lazarev.repository.ParametersIsland;
import com.javarush.island.lazarev.repository.ProbabilityTable;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static com.javarush.island.lazarev.repository.ParametersIsland.REQUIRED_POPULATION_FOR_REPRODUCTION;

public abstract class Animal extends Nature{

    public Animal(TileIcon iconType, NatureParameters natureParameters, Coordinates coordinates, Location location,
                  ProbabilityTable probabilityTable, Island island) {
        super(iconType, natureParameters, coordinates, location, probabilityTable, island);
    }

    public void setCurrentCoordinates(Coordinates newCoordinates) {
        this.coordinates = newCoordinates;
    }


    public synchronized void move() {
        if (!isAlive()) {
            return;
        }
        if (natureParameters.getSpeed() > 0) {
            int newX = getCoordinates().x() + ThreadLocalRandom.current().nextInt(natureParameters.getSpeed());
            int newY = getCoordinates().y() + ThreadLocalRandom.current().nextInt(natureParameters.getSpeed());

            newX = Math.max(0, Math.min(newX, ParametersIsland.ISLAND_ROWS - 1));
            newY = Math.max(0, Math.min(newY, ParametersIsland.ISLAND_COLS - 1));

            Coordinates newCoordinates = new Coordinates(newX, newY);
            setCurrentCoordinates(newCoordinates);
        }
    }



    public EntityType getEntityType() {
        return super.getEntityType();
    }
    public synchronized void reproduceIfPossible(Location location) {
        List<Nature> entitiesInCellCopy = new ArrayList<>(location.getEntitiesAt(coordinates));


        if (entitiesInCellCopy.size() >= REQUIRED_POPULATION_FOR_REPRODUCTION ) {
            EntityType entityType = EntityType.valueOf(this.getClass().getSimpleName().toUpperCase());
            Nature offspring = EntityFactory.createOffspring(entityType, coordinates, location, probabilityTable, island);
            location.addEntity(offspring);
        }
    }
    }




