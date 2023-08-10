package com.javarush.island.lazarev.view;

import com.javarush.island.lazarev.entities.Nature;
import com.javarush.island.lazarev.location.Location;

import java.util.List;

public class ConsoleView implements View {

    @Override
    public void displayIsland(Location[][] locations) {
        System.out.println("Содержимое острова:");

        for (Location[] locationRow : locations) {
            for (Location location : locationRow) {
                List<Nature> cellContents = location.getEntities();
                if (cellContents.isEmpty()) {
                    System.out.print("  .  ");
                } else {
                    Nature lastEntity = cellContents.get(cellContents.size() - 1);
                    System.out.print(" " + lastEntity.getIconType().getIcon() + " ");
                }
            }
            System.out.println();
        }
    }
}