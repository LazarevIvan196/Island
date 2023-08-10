package com.javarush.island.lazarev.entities;

import com.javarush.island.lazarev.location.Island;
import com.javarush.island.lazarev.location.Location;
import com.javarush.island.lazarev.location.Coordinates;
import com.javarush.island.lazarev.repository.NatureParameters;
import com.javarush.island.lazarev.repository.ProbabilityTable;
import java.util.concurrent.ThreadLocalRandom;



    public abstract class Nature {

        protected final TileIcon iconType;
        protected NatureParameters natureParameters;
        protected Island island;
        protected boolean isAlive = true;
        protected Coordinates coordinates;
        protected Location location;
        protected ProbabilityTable probabilityTable;

        public Nature(TileIcon iconType, NatureParameters natureParameters, Coordinates coordinates,
                      Location location, ProbabilityTable probabilityTable, Island island) {
            this.iconType = iconType;
            this.coordinates = coordinates;
            this.natureParameters = natureParameters;
            this.location = location;
            this.probabilityTable = probabilityTable;
            this.island = island;
        }

        public TileIcon getIconType() {
            return iconType;
        }

        public boolean isAlive() {
            return isAlive;
        }

        public void setAlive(boolean alive) {
            isAlive = alive;
        }

        public Coordinates getCoordinates() {
            return coordinates;
        }

        public Location getLocation() {
            return location;
        }

        public NatureParameters getNatureParameters() {
            return natureParameters;
        }

        public EntityType getEntityType() {
            return EntityType.valueOf(this.getClass().getSimpleName().toUpperCase());
        }

        public synchronized void eat() {
            if (isAlive()) {
                Nature food = location.findFoodOnSameCell(this);
                if (food != null) {
                    EntityType predatorType = getEntityType();
                    EntityType preyType = food.getEntityType();

                    int predationProbability = probabilityTable.getPredationProbability(predatorType, preyType);
                    int randomNum = ThreadLocalRandom.current().nextInt(100);

                    if (randomNum < predationProbability) {
                        double foodWeight = food.getNatureParameters().getWeight();
                        double foodNeeded = natureParameters.getFoodNeeded();

                        if (foodWeight <= foodNeeded) {
                            // System.out.println("Predator type: " + predatorType +
                            //                    " is eating. Food needed: " + foodNeeded);
                            increaseFoodNeeded(foodWeight);
                            location.removeEntity(food);
                        } else {
                            // System.out.println("Predator type: " + predatorType +
                            //                    " is eating. Food needed: " + foodNeeded +
                            //                    " but food weight is: " + foodWeight);
                            restoreFoodNeeded();
                        }
                    }
                }
                decreaseFoodNeeded();
            }
        }

        public synchronized void decreaseFoodNeeded() {
            double originalFoodNeeded = natureParameters.getFoodNeeded();
            double newFoodNeeded = Math.max(originalFoodNeeded - (originalFoodNeeded * 0.10), 0);

            if (newFoodNeeded <= 0.0001) {
                setAlive(false);
            }

            natureParameters.setFoodNeeded(newFoodNeeded);
        }

        public synchronized void restoreFoodNeeded() {
            EntityType entityType = getEntityType();
            natureParameters.restoreFoodNeeded(entityType);
        }

        public synchronized void increaseFoodNeeded(double foodWeight) {
            EntityType entityType = getEntityType();
            natureParameters.increaseFoodNeeded(entityType, foodWeight);
        }

    }