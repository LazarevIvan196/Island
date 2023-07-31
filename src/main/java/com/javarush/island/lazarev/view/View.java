package com.javarush.island.lazarev.view;

import com.javarush.island.lazarev.entities.Nature;


import java.util.List;

public interface View {
    void displayIsland(List<Nature>[][] grid);
}
