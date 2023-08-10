package com.javarush.island.lazarev.entities.predators;

import com.javarush.island.lazarev.entities.TileIcon;
import com.javarush.island.lazarev.location.Coordinates;
import com.javarush.island.lazarev.location.Island;
import com.javarush.island.lazarev.location.Location;
import com.javarush.island.lazarev.repository.NatureParameters;
import com.javarush.island.lazarev.repository.ProbabilityTable;

public class Bear extends Predator {


    public Bear(TileIcon iconType, NatureParameters natureParameters, Coordinates coordinates, Location location, ProbabilityTable probabilityTable, Island island) {
        super(iconType, natureParameters, coordinates, location, probabilityTable, island);
    }

    @Override
    public TileIcon getIconType() {
        return TileIcon.BEAR;
    }

 }
