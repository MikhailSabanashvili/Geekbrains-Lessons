package com.geekbrains;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        zeroOneArray();
        oneHundredLengthArray();
        task3();
        task4();
        System.out.println(Arrays.toString(task5(6, 4)));
    }

    public static void zeroOneArray(){
        int[] arr = {1, 0, 0, 0, 1, 0};
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i] == 0 ? 1 : 0;
        }
        System.out.println(Arrays.toString(arr));
    }

    public static void oneHundredLengthArray(){
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
        System.out.println(Arrays.toString(arr));
    }

    public static void task3(){
        int[] arr = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] < 6)
                arr[i]*=2;
        }
        System.out.println(Arrays.toString(arr));
    }

    public static void task4(){
        int[][] arr = new int[3][3];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if(i == 0 && j == 0)
                    arr[i][j] = 1;
                else if(i == 1 && j == 1)
                    arr[i][j] = 1;
                else if(i == 2 && j == 2)
                    arr[i][j] = 1;
                else if(i == 2 && j == 0)
                    arr[i][j] = 1;
                else if(i == 0 && j == 2)
                    arr[i][j] = 1;
            }
        }

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }
    }

    public static int[] task5(int len, int initValue){
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = initValue;
        }
        return arr;
    }
}
