package com.javarush.island.lazarev;

import com.javarush.island.lazarev.location.Island;
import com.javarush.island.lazarev.location.Location;
import com.javarush.island.lazarev.view.ConsoleView;
import com.javarush.island.lazarev.view.GuiView;
import com.javarush.island.lazarev.view.View;


public class EntryPoint {

    public static void main(String[] args) {
        int rows = 100;
        int cols = 20;

        View consoleView = new ConsoleView();
        View guiView = new GuiView();

        Location location = new Location(rows, cols);

        Island island1 = new Island(rows, cols, consoleView, location);
        Island island2 = new Island(rows, cols, guiView, location);

        island1.populateIsland();
//        island2.populateIsland();
    }
}