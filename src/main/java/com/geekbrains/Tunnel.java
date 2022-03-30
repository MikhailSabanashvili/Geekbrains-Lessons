package com.geekbrains;

public class Tunnel extends Stage {
    public Tunnel() {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
    }
    @Override
    public void go(Car c) {
        try {
            try {
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                //Car.getCyclicBarrier().await();
                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                    String s = c.getName() + " закончил этап: " + description;
                    System.out.println(s);
                    //Car.getCyclicBarrier().await();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
