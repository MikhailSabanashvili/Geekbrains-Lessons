package com.geekbrains;

public class Plate {
    private int food;

    public Plate(int food) {
        this.food = food;
    }

    public void decreaseFood(int n, Cat cat) {
        if (food < n)
            System.out.println("В тарелке не хватает еды");
        else {
            food -= n;
            cat.setSatiety(true);
        }
    }

    public void info() {
        System.out.println("plate: " + food);
    }

    public void addFood(int food) {
        this.food += food;
    }
}
