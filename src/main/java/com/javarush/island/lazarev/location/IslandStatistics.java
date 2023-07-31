package com.javarush.island.lazarev.location;

import com.javarush.island.lazarev.entities.Nature;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IslandStatistics {
    private Map<Class<? extends Nature>, Integer> entityCountMap;

    public IslandStatistics() {
        entityCountMap = new HashMap<>();
    }

    public void collectStatistics(List<Nature>[][] grid) {
        entityCountMap.clear();

        for (List<Nature>[] lists : grid) {
            for (List<Nature> cellContents : lists) {
                for (Nature entity : cellContents) {
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
}