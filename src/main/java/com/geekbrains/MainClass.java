package com.geekbrains;

import java.util.Arrays;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class MainClass {
    public static final int CARS_COUNT = 4;
    public static Queue<Integer> globalQueue = new ConcurrentLinkedQueue<>();
    public static Race race1;
    public static void main(String[] args) throws InterruptedException {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        race1 = race;
        Car[] cars = new Car[CARS_COUNT];
        Car.setCyclicBarrier(CARS_COUNT);
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
        }
        Thread[] threads = new Thread[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            Thread thread = new Thread(cars[i]);
            threads[i] = thread;
            thread.start();
        }

        while(true) {
            if(globalQueue.size() == CARS_COUNT)
                break;
        }
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");

        Arrays.stream(threads).forEach(x -> {
            try {
                x.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }
}
