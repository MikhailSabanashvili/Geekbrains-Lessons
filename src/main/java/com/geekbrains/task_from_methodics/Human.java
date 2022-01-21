package com.geekbrains.task_from_methodics;

public class Human implements Movable {
    private int jumpAbility;
    private int runAbility;

    public Human(int jumpAbility, int runAbility) {
        this.jumpAbility = jumpAbility;
        this.runAbility = runAbility;
    }

    @Override
    public boolean run(Track track) {
        if(runAbility >= track.getLength()) {
            System.out.println("Человек пробежал " + track.getLength() + " метров");
            return true;
        }
        return false;
    }

    @Override
    public boolean jump(Wall wall) {
        if(jumpAbility >= wall.getLength()) {
            System.out.println("Человек прыгнул " + wall.getLength() + " метров");
            return true;
        }
        return false;
    }
}
