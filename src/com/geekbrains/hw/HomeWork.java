package com.geekbrains.hw;

public class HomeWork {
    private static volatile char letter = 'C';
    private static Object monitor1 = new Object();
    public static void printA() throws InterruptedException {
        while (true) {
            synchronized (monitor1) {
                while (letter != 'C')
                    monitor1.wait();
                letter = 'A';
                System.out.print(letter);
                monitor1.notifyAll();
            }
        }
    }

    public static void printB() throws InterruptedException {
        while (true) {
            synchronized (monitor1) {
                while (letter != 'A')
                    monitor1.wait();

                letter = 'B';
                System.out.print(letter);
                monitor1.notifyAll();
            }
        }
    }

    public static void printC() throws InterruptedException {
        while (true) {
            synchronized (monitor1) {
                while (letter != 'B')
                    monitor1.wait();

                letter = 'C';
                System.out.print(letter);
                monitor1.notifyAll();
            }
        }
    }

    public static void main(String[] args) {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    printA();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread1.start();

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    printB();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread2.start();

        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    printC();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread3.start();
    }
}
