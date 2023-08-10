package com.javarush.island.lazarev.factory;

import com.javarush.island.lazarev.entities.EntityType;
import com.javarush.island.lazarev.entities.Nature;
import com.javarush.island.lazarev.entities.TileIcon;
import com.javarush.island.lazarev.entities.predators.*;
import com.javarush.island.lazarev.location.Coordinates;
import com.javarush.island.lazarev.location.Island;
import com.javarush.island.lazarev.location.Location;
import com.javarush.island.lazarev.repository.NatureParameters;
import com.javarush.island.lazarev.repository.ProbabilityTable;


public class PredatorsCreator implements Factory {
    private final EntityType entityType;
    private final Coordinates coordinates;
    private final Location location;
    private final ProbabilityTable probabilityTable;
    private final Island island;

    public PredatorsCreator(EntityType entityType, Coordinates coordinates, Location location, ProbabilityTable probabilityTable, Island island) {
        this.entityType = entityType;
        this.coordinates = coordinates;
        this.location = location;
        this.probabilityTable = probabilityTable;
        this.island = island;
    }

    @Override
    public Nature create(NatureParameters parameters) {
        return switch (entityType) {
            case BEAR -> new Bear(TileIcon.BEAR, parameters, coordinates, location, probabilityTable,island);
            case EAGLE -> new Eagle(TileIcon.EAGLE, parameters, coordinates, location, probabilityTable,island);
            case FOX -> new Fox(TileIcon.FOX, parameters, coordinates, location, probabilityTable,island);
            case TIGER -> new Tiger(TileIcon.TIGER, parameters, coordinates, location, probabilityTable,island);
            case WOLF -> new Wolf(TileIcon.WOLF, parameters, coordinates, location, probabilityTable,island);
            default -> throw new IllegalArgumentException("Unknown entity type: " + entityType);
        };
    }
}

