package com.javarush.island.lazarev.factory;

import com.javarush.island.lazarev.entities.EntityType;
import com.javarush.island.lazarev.entities.Nature;
import com.javarush.island.lazarev.entities.TileIcon;
import com.javarush.island.lazarev.entities.herbivores.*;
import com.javarush.island.lazarev.location.Coordinates;
import com.javarush.island.lazarev.location.Island;
import com.javarush.island.lazarev.location.Location;
import com.javarush.island.lazarev.repository.NatureParameters;
import com.javarush.island.lazarev.repository.ProbabilityTable;

public class HerbivoresCreator implements Factory {
    private final EntityType entityType;
    private final Coordinates coordinates;
    private final Location location;
    private final ProbabilityTable probabilityTable;
    private final Island island;

    public HerbivoresCreator(EntityType entityType, Coordinates coordinates, Location location, ProbabilityTable probabilityTable, Island island) {
        this.entityType = entityType;
        this.coordinates = coordinates;
        this.location = location;
        this.probabilityTable = probabilityTable;
        this.island = island;
    }

    @Override
    public Nature create(NatureParameters parameters) {
        return switch (entityType) {
            case BEE -> new Bee(TileIcon.BEE, parameters, coordinates, location, probabilityTable, island);
            case BOAR -> new Boar(TileIcon.BOAR, parameters, coordinates, location, probabilityTable,island);
            case DEER -> new Deer(TileIcon.DEER, parameters, coordinates, location, probabilityTable,island);
            case DUCK -> new Duck(TileIcon.DUCK, parameters, coordinates, location, probabilityTable,island);
            case HARE -> new Hare(TileIcon.HARE, parameters, coordinates, location, probabilityTable,island);
            case MOUSE -> new Mouse(TileIcon.MOUSE, parameters, coordinates, location, probabilityTable,island);
            case SHEEP -> new Sheep(TileIcon.SHEEP, parameters, coordinates, location, probabilityTable,island);
            case RABBIT -> new Rabbit(TileIcon.RABBIT, parameters, coordinates, location, probabilityTable,island);
            case BEAVER -> new Beaver(TileIcon.BEAVER, parameters, coordinates, location, probabilityTable,island);
            case CATERPILLAR -> new Caterpillar(TileIcon.CATERPILLAR, parameters, coordinates, location, probabilityTable,island);
            default -> throw new IllegalArgumentException("Unknown entity type: " + entityType);
        };
    }
}