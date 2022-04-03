package com.geekbrains.hw;

import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HomeWorkTest {

    @Test
    void getArr1() {
        int[] arr = {1, 2, 4, 4, 2, 3, 4, 1, 7};
        int[] arrCopy = HomeWork.getArr(arr);
        assertArrayEquals(new int[]{1, 7}, arrCopy);
    }

    @Test
    void getArr2() {
        int[] arr = {1, 2, 3, 4, 2, 3, 1, 7};
        int[] arrCopy = HomeWork.getArr(arr);
        assertArrayEquals(new int[]{2, 3, 1, 7}, arrCopy);
    }

    @Test
    void getArr3() {
        int[] arr = {1, 4, 3, 2, 3, 1, 7};
        int[] arrCopy = HomeWork.getArr(arr);
        assertArrayEquals(new int[]{3, 2, 3, 1, 7}, arrCopy);
    }

    @Test
    void isExist1() {
        int[] arr = {1, 4, 3, 2, 3, 1, 7};
        boolean x = HomeWork.isExist(arr);
        assertTrue(x);
    }

    @Test
    void isExist2() {
        int[] arr = {1, 3, 2, 3, 7};
        boolean x = HomeWork.isExist(arr);
        assertFalse(x);
    }

    @Test
    void isExist3() {
        int[] arr = {3, 2, 3, 7};
        boolean x = HomeWork.isExist(arr);
        assertFalse(x);
    }

    @Test
    void isExist4() {
        int[] arr = {};
        boolean x = HomeWork.isExist(arr);
        assertFalse(x);
    }

}