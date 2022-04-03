package com.geekbrains.hw;

import java.util.Arrays;

public class HomeWork {
    static int[] getArr(int[] arr) {
        if(Arrays.stream(arr).noneMatch(x -> x == 4))
            throw new RuntimeException();

        int[] arrCopy = null;
        for (int i = arr.length - 1, count = 0; i > 0; i--, count++) {
            if(arr[i] == 4) {
                arrCopy = new int[count];
                for (int j = i + 1, z = 0; j < arr.length; j++, z++) {
                    arrCopy[z] = arr[j];
                }
                break;
            }
        }

        return arrCopy;
    }

    static boolean isExist(int[] arr) {
        if(Arrays.stream(arr).noneMatch(x -> x == 4) || Arrays.stream(arr).noneMatch(x -> x == 1))
            return false;
        return true;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 4, 4, 2, 3, 4, 7};
        System.out.println(Arrays.toString(getArr(arr)));

        System.out.println(isExist(arr));
    }
}
