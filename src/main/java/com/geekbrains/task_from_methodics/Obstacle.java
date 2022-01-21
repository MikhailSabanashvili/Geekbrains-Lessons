package com.geekbrains.task_from_methodics;

public abstract class Obstacle {
    protected int length;

    public Obstacle(int length) {
        this.length = length;
    }

    public int getLength() {
        return length;
    }
}
