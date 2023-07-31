package com.javarush.island.lazarev.entities.plant;



public enum Plants {
    WOOD(0, 10),
    GRAS(0, 200);

    private final int minCount;
    private final int maxCount;

    Plants(int minCount, int maxCount) {
        this.minCount = minCount;
        this.maxCount = maxCount;
    }

    public int getMinCount() {
        return minCount;
    }

    public int getMaxCount() {
        return maxCount;
    }
}
