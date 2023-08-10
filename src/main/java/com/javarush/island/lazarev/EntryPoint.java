package com.javarush.island.lazarev;

import com.javarush.island.lazarev.location.Island;

import com.javarush.island.lazarev.repository.ParametersIsland;
import com.javarush.island.lazarev.view.ConsoleView;
import com.javarush.island.lazarev.view.GuiView;
import com.javarush.island.lazarev.view.View;

import static com.javarush.island.lazarev.repository.ParametersIsland.SIMULATION_SPEED;

public class EntryPoint {
    public static void main(String[] args) {
        View consoleView = new ConsoleView();
        View guiView = new GuiView();

        Island islandConsole = new Island(ParametersIsland.ISLAND_ROWS, ParametersIsland.ISLAND_COLS, consoleView);
        Island islandGui = new Island(ParametersIsland.ISLAND_ROWS, ParametersIsland.ISLAND_COLS, guiView);


        islandConsole.populateIsland();
        islandGui.populateIsland();

//     islandConsole.startSimulation(SIMULATION_SPEED);
        islandGui.startSimulation(SIMULATION_SPEED);
        islandGui.stopSimulation();
//        islandConsole.stopSimulation();
    }

}
