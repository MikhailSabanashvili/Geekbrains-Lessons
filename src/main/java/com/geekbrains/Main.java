package com.geekbrains;

import java.util.Arrays;

public class Main {
    static final int size = 10000000;
    static final int h = size / 2;

    public static void createArray1() {
        float[] arr = new float[size];

        Arrays.fill(arr, 1f);

        long a = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }

        System.out.println((System.currentTimeMillis() - a) + " Вызов метода createArray1");
    }

    public static void createArray2() {
        float[] arr = new float[size];
        float[] arr1 = new float[h];
        float[] arr2 = new float[h];

        Arrays.fill(arr, 1f);

        long a = System.currentTimeMillis();
        System.arraycopy(arr, 0, arr1, 0, h);
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < arr1.length; i++) {
                    arr1[i] = (float)(arr1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
            }
        }).start();
        System.arraycopy(arr, h, arr2, 0, h);
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < arr2.length; i++) {
                    arr2[i] = (float)(arr2[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
            }
        }).start();

        System.arraycopy(arr1, 0, arr, 0, h);
        System.arraycopy(arr2, 0, arr, h, h);

        System.out.println((System.currentTimeMillis() - a) + " Вызов метода createArray2");
    }

    public static void main(String[] args) {
        createArray1();
        createArray2();
    }
}
