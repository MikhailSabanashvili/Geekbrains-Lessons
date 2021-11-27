package com.geekbrains;

public class Cat {
    private String name;
    private int appetite;
    private boolean satiety;

    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
        this.satiety = false;
    }

    public void eat(Plate p) {
        p.decreaseFood(appetite, this);
    }

    public void setSatiety(boolean satiety) {
        this.satiety = satiety;
    }

    public boolean isSatiety() {
        return satiety;
    }
}
