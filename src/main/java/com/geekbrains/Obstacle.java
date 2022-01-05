package com.geekbrains;

public abstract class Obstacle {
    protected int length;

    public Obstacle(int length) {
        this.length = length;
    }

    public int getLength() {
        return length;
    }
}
