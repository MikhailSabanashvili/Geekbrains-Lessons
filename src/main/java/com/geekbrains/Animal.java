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

    abstract void run(int metres);
    abstract void swim(int metres);
}
