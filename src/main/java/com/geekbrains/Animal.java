package com.geekbrains;

public abstract class Animal {
    private static int countAnimals;
    protected String name;

    public Animal(String name) {
        this.name = name;
        countAnimals++;
    }

    public static int numberAnimals() {
        return countAnimals;
    }

    public abstract void run(int metres);
    public abstract void swim(int metres);
}
