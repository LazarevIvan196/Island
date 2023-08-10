package com.javarush.island.lazarev.location;

import com.javarush.island.lazarev.entities.Nature;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class IslandStatistics {
    private final Map<Class<? extends Nature>, Integer> entityCountMap;

    public IslandStatistics() {
        entityCountMap = new HashMap<>();
    }

    public void collectStatistics(Location[][] locations) {
        entityCountMap.clear();

        for (Location[] locationRow : locations) {
            for (Location location : locationRow) {
                for (Nature entity : location.getEntities()) {
                    Class<? extends Nature> entityType = entity.getClass();
                    entityCountMap.put(entityType, entityCountMap.getOrDefault(entityType, 0) + 1);
                }
            }
        }
    }


    public void printStatistics() {
        System.out.println("Общая статистика на острове:");
        for (Map.Entry<Class<? extends Nature>, Integer> entry : entityCountMap.entrySet()) {
            Class<? extends Nature> entityType = entry.getKey();
            int count = entry.getValue();
            System.out.println("Количество " + entityType.getSimpleName() + ": " + count);
        }
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IslandStatistics that = (IslandStatistics) o;
        return Objects.equals(entityCountMap, that.entityCountMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(entityCountMap);
    }
}