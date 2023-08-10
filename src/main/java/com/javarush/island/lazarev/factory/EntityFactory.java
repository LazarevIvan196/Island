package com.javarush.island.lazarev.factory;


import com.javarush.island.lazarev.entities.EntityType;
import com.javarush.island.lazarev.entities.Nature;
import com.javarush.island.lazarev.location.Coordinates;
import com.javarush.island.lazarev.location.Island;
import com.javarush.island.lazarev.location.Location;
import com.javarush.island.lazarev.repository.NatureParameters;
import com.javarush.island.lazarev.repository.ProbabilityTable;

import java.util.concurrent.ThreadLocalRandom;

public class EntityFactory {
    private final Location[][] locations;
    private final ProbabilityTable probabilityTable;
    private final Island island;

    public EntityFactory(Location[][] locations, ProbabilityTable probabilityTable, Island island) {
        this.locations = locations;
        this.probabilityTable = probabilityTable;
        this.island = island;
    }

    public void createPlants() {
        for (EntityType entityType : EntityType.getPlantTypes()) {
            NatureParameters parameters = NatureParameters.getAnimalParameters(entityType);
            int numEntities = ThreadLocalRandom.current().nextInt(parameters.getMaxEntitiesPerCell());
            for (int i = 0; i < numEntities; i++) {
                Coordinates coordinates = generateRandomCoordinates();
                PlantCreator plantCreator = new PlantCreator(entityType, coordinates, locations[coordinates.x()][coordinates.y()],
                        probabilityTable, island);
                Nature entity = plantCreator.create(parameters);
                placeEntity(entity);
            }
        }
    }

    public void createHerbivores() {
        for (EntityType entityType : EntityType.getHerbivoreTypes()) {
            NatureParameters parameters = NatureParameters.getAnimalParameters(entityType);
            int numEntities = ThreadLocalRandom.current().nextInt(parameters.getMaxEntitiesPerCell());
            for (int i = 0; i < numEntities; i++) {
                Coordinates coordinates = generateRandomCoordinates();
                HerbivoresCreator herbivoresCreator = new HerbivoresCreator(entityType, coordinates, locations[coordinates.x()][coordinates.y()],
                        probabilityTable, island);
                Nature entity = herbivoresCreator.create(parameters);
                placeEntity(entity);
            }
        }
    }

    public void createPredators() {
        for (EntityType entityType : EntityType.getPredatorTypes()) {
            NatureParameters parameters = NatureParameters.getAnimalParameters(entityType);
            int numEntities = ThreadLocalRandom.current().nextInt(parameters.getMaxEntitiesPerCell());
            for (int i = 0; i < numEntities; i++) {
                Coordinates coordinates = generateRandomCoordinates();
                PredatorsCreator predatorsCreator = new PredatorsCreator(entityType, coordinates, locations[coordinates.x()][coordinates.y()],
                        probabilityTable, island);
                Nature entity = predatorsCreator.create(parameters);
                placeEntity(entity);
            }
        }
    }

    private Coordinates generateRandomCoordinates() {
        int row = ThreadLocalRandom.current().nextInt(locations.length);
        int col = ThreadLocalRandom.current().nextInt(locations[0].length);
        return new Coordinates(row, col);
    }

    private void placeEntity(Nature entity) {
        int row = entity.getCoordinates().x();
        int col = entity.getCoordinates().y();
        locations[row][col].addEntity(entity);
    }
    public static Nature createOffspring(EntityType entityType, Coordinates coordinates, Location location, ProbabilityTable probabilityTable, Island island) {
        NatureParameters parameters = NatureParameters.getAnimalParameters(entityType);

        if (EntityType.getPlantTypes().contains(entityType)) {
            PlantCreator plantCreator = new PlantCreator(entityType, coordinates, location, probabilityTable, island);
            return plantCreator.create(parameters);
        } else if (EntityType.getHerbivoreTypes().contains(entityType)) {
            HerbivoresCreator herbivoresCreator = new HerbivoresCreator(entityType, coordinates, location, probabilityTable, island);
            return herbivoresCreator.create(parameters);
        } else if (EntityType.getPredatorTypes().contains(entityType)) {
            PredatorsCreator predatorsCreator = new PredatorsCreator(entityType, coordinates, location, probabilityTable, island);
            return predatorsCreator.create(parameters);
        }

        return null;
    }


}