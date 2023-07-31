package com.javarush.island.lazarev.view;

import com.javarush.island.lazarev.entities.Nature;
import java.util.List;

public class ConsoleView implements View {

    @Override
    public void displayIsland(List<Nature>[][] grid) {
        System.out.println("Содержимое острова:");

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                List<Nature> cellContents = grid[i][j];
                if (cellContents.isEmpty()) {
                    System.out.print("  .  "); // Пустая плитка
                } else {
                    Nature lastEntity = cellContents.get(cellContents.size() - 1);
                    System.out.print(" " + lastEntity.getIconType().getIcon() + " ");
                }
            }
            System.out.println();
        }
    }
}