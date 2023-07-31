package com.javarush.island.lazarev.entities.herbivores;

public enum Herbivores {
    RABBIT(0, 150),
    MOUSE(0, 500),
    DEER(0, 20),
    BOAR(0, 50),
    HARE(0, 100),
    DUCK(0, 200),
    CATERPILLAR(0, 1000),
    BEE(0, 700),
    SHEEP(0, 140),
    BEAVER(0, 130);

    private final int minCount;
    private final int maxCount;

    Herbivores(int minCount, int maxCount) {
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
