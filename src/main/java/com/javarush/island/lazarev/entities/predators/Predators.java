package com.javarush.island.lazarev.entities.predators;

public enum Predators {
    BEAR(0, 5),
    EAGLE(0, 6),
    FOX(0, 3),
    TIGER(0, 3),
    WOLF(0, 3);

    private final int minCount;
    private final int maxCount;

    Predators(int minCount, int maxCount) {
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
