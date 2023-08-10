package com.javarush.island.lazarev.repository;

import com.javarush.island.lazarev.entities.EntityType;

public class ProbabilityTable {

    private final int[][] probabilityTable;


    public ProbabilityTable() {
        int size = EntityType.values().length;
        this.probabilityTable = new int[size][size];
        probabilityTable[EntityType.WOLF.ordinal()] = new int[]{0, 0, 0, 0, 0, 25, 15, 60, 80, 60, 70, 15, 40, 0, 0, 0, 0};
        probabilityTable[EntityType.TIGER.ordinal()] = new int[]{10, 0, 25, 5, 0, 30, 70, 90, 100, 85, 80, 30, 40, 0, 0, 0, 0};
        probabilityTable[EntityType.FOX.ordinal()] = new int[]{0, 0, 0, 0, 0, 0, 0, 70, 90, 60, 0, 0, 60, 40, 0, 0, 0};
        probabilityTable[EntityType.BEAR.ordinal()] = new int[]{0, 5, 0, 0, 0, 35, 80, 80, 90, 80, 70, 50, 10, 0, 0, 0, 0};
        probabilityTable[EntityType.EAGLE.ordinal()] = new int[]{0, 0, 10, 0, 0, 40, 0, 90, 90, 80, 0, 0, 80, 0, 0, 0, 0};
        probabilityTable[EntityType.BEAVER.ordinal()] = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100, 10};
        probabilityTable[EntityType.DEER.ordinal()] = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 100};
        probabilityTable[EntityType.RABBIT.ordinal()] = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 100};
        probabilityTable[EntityType.MOUSE.ordinal()] = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 90, 0, 0, 100};
        probabilityTable[EntityType.HARE.ordinal()] = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 100};
        probabilityTable[EntityType.BOAR.ordinal()] = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 50, 0, 0, 0, 0, 90, 0, 20, 100};
        probabilityTable[EntityType.DUCK.ordinal()] = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 90, 20, 0, 100};
        probabilityTable[EntityType.BEE.ordinal()] = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100, 100};
        probabilityTable[EntityType.CATERPILLAR.ordinal()] = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100, 100};


    }

    public int getPredationProbability(EntityType predator, EntityType prey) {
        int predatorIndex = predator.ordinal();
        int preyIndex = prey.ordinal();
        return probabilityTable[predatorIndex][preyIndex];
    }


}
