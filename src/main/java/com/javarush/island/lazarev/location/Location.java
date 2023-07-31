package com.javarush.island.lazarev.location;

import com.javarush.island.lazarev.entities.Nature;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Location extends JPanel {
    private final int rows;
    private final int cols;
    private List<Nature>[][] grid;

    public Location(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.grid = new List[rows][cols];
        initializeGrid();
    }

    public void updateGrid(List<Nature>[][] grid) {
        this.grid = grid;
        repaint();
    }

    private void initializeGrid() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = new ArrayList<>();
            }
        }
    }
    public List<Nature>[][] getGrid() {
        return grid;
    }

    public void addEntity(Nature entity, int row, int col) {
        grid[row][col].add(entity);
    }

    public void removeEntity(Nature entity, int row, int col) {
        grid[row][col].remove(entity);
    }
}