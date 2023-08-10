package com.javarush.island.lazarev.factory;

import com.javarush.island.lazarev.entities.EntityType;
import com.javarush.island.lazarev.entities.Nature;
import com.javarush.island.lazarev.entities.TileIcon;
import com.javarush.island.lazarev.entities.plant.Grass;
import com.javarush.island.lazarev.entities.plant.Woods;
import com.javarush.island.lazarev.location.Coordinates;
import com.javarush.island.lazarev.location.Island;
import com.javarush.island.lazarev.location.Location;
import com.javarush.island.lazarev.repository.NatureParameters;
import com.javarush.island.lazarev.repository.ProbabilityTable;

public class PlantCreator implements Factory {
    private final EntityType entityType;
    private final Coordinates coordinates;
    private final Location location;
    private final ProbabilityTable probabilityTable;
    private final Island island;

    public PlantCreator(EntityType entityType, Coordinates coordinates, Location
            location,ProbabilityTable probabilityTable, Island island) {
        this.entityType = entityType;
        this.coordinates = coordinates;
        this.location = location;
        this.island = island;
        this.probabilityTable = probabilityTable;
    }

    @Override
    public Nature create(NatureParameters parameters) {
        return switch (entityType) {
            case WOODS -> new Woods(TileIcon.WOODS, parameters, coordinates, location,probabilityTable, island);
            case GRASS -> new Grass(TileIcon.GRASS, parameters, coordinates, location,probabilityTable, island);
            default -> throw new IllegalArgumentException("Unknown entity type: " + entityType);
        };
    }
}