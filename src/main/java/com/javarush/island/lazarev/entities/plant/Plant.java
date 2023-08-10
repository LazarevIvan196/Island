package com.javarush.island.lazarev.entities.plant;

import com.javarush.island.lazarev.entities.Nature;
import com.javarush.island.lazarev.entities.TileIcon;
import com.javarush.island.lazarev.location.Coordinates;
import com.javarush.island.lazarev.location.Island;
import com.javarush.island.lazarev.location.Location;
import com.javarush.island.lazarev.repository.NatureParameters;
import com.javarush.island.lazarev.repository.ProbabilityTable;


public abstract class Plant extends Nature {
protected Location location;

    public Plant(TileIcon iconType, NatureParameters natureParameters, Coordinates coordinates, Location location, ProbabilityTable probabilityTable, Island island) {
        super(iconType, natureParameters, coordinates, location, probabilityTable, island);
    }
}
