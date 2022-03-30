package com.geekbrains;

public class Road extends Stage {
    private static Object monitor = new Object();
    private static volatile boolean flag = true;
    public Road(int length) {
        this.length = length;
        this.description = "Дорога " + length + " метров";
    }
    @Override
    public void go(Car c) {
        try {
            System.out.println(c.getName() + " начал этап: " + description);
            Thread.sleep(length / c.getSpeed() * 1000);
            Road road = (Road) MainClass.race1.getStages().get(MainClass.race1.getStages().size() - 1);
            System.out.println(c.getName() + " закончил этап: " + description);
            if(road.getDescription().equals(description)) {
                synchronized (monitor) {
                    if (flag) {
                        System.out.println(c.getName() + "  WIN!");
                        flag = false;
                    }
                }
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
