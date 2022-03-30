package com.geekbrains;

import java.util.concurrent.CyclicBarrier;

public class Car implements Runnable {
    private static CyclicBarrier cyclicBarrier;
    private static int CARS_COUNT;
    static {
        CARS_COUNT = 0;
    }
    private Race race;
    private int speed;
    private String name;

    public static CyclicBarrier getCyclicBarrier() {
        return cyclicBarrier;
    }

    public String getName() {
        return name;
    }
    public int getSpeed() {
        return speed;
    }
    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }
    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            cyclicBarrier.await();
            System.out.println(this.name + " готов");
            cyclicBarrier.await();
            MainClass.globalQueue.add(1);
            cyclicBarrier.await();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }
    }

    public static void setCyclicBarrier(int count) {
        Car.cyclicBarrier = new CyclicBarrier(count);
    }
}
