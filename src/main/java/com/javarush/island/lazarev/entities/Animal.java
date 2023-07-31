package com.javarush.island.lazarev.entities;


public abstract class Animal extends Nature {
    private FoodType foodType;

    private int moveSpeed;
    private double foodNeeded;
    private double probabilityToFindFood;

    public Animal(TileIcon tileIcon, double weight, double probabilityToBeEaten, FoodType foodType, int moveSpeed, double foodNeeded, double probabilityToFindFood) {
        super(tileIcon, weight, probabilityToBeEaten);
        this.foodType = foodType;
        this.moveSpeed = moveSpeed;
        this.foodNeeded = foodNeeded;
        this.probabilityToFindFood = probabilityToFindFood;
    }

    public FoodType getFoodType() {
        return foodType;
    }

    public int getMoveSpeed() {
        return moveSpeed;
    }

    public void setMoveSpeed(int moveSpeed) {
        this.moveSpeed = moveSpeed;
    }

    public double getFoodNeeded() {
        return foodNeeded;
    }

    public void setFoodNeeded(double foodNeeded) {
        this.foodNeeded = foodNeeded;
    }

    public double getProbabilityToFindFood() {
        return probabilityToFindFood;
    }

    public void setProbabilityToFindFood(double probabilityToFindFood) {
        this.probabilityToFindFood = probabilityToFindFood;
    }

    public abstract void moveUp();

    public abstract void moveDown();

    public abstract void moveLeft();

    public abstract void moveRight();

    public void eat(Nature food) {
        if (food.getType() == foodType) {
            double foodWeight = food.getWeight();
            if (foodWeight >= foodNeeded) {
                System.out.println("Animal is eating.");
                setWeight(getWeight() + foodNeeded);
            } else {
                System.out.println("Not enough food for the animal.");
            }
        } else {
            System.out.println("Animal cannot eat this type of food.");
        }
    }

}

