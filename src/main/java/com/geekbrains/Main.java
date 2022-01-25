package com.geekbrains;

public class Main {
    public static int count;
    public static void main(String[] args) {
//        String[][] arr = {{"1", "2", "3", "4"},
//                {"5", "6", "7", "8"},
//                {"9", "0", "10", "11"},
//                {"12", "13", "d", "15"}};

        String[][] arr = {{"1", "2", "3", "4"},
                {"9", "0", "10", "11"},
                {"12", "13", "d", "15"}};
        try {
            checkArray(arr);
        } catch (MyArrayDataException | MyArraySizeException myArrayException) {
            System.out.println(myArrayException.getMessage());
        } finally {
            System.out.println("Результат расчета: " + count);
        }

    }

    public static void checkArray(String[][] arr) throws MyArraySizeException, MyArrayDataException {
        if (arr.length == 4) {
            for (int i = 0; i < arr.length; i++) {
                if(arr[i].length != 4)
                    throw new MyArraySizeException("Массив другого размера...");
            }
            System.out.println("Массив верного размера");
        } else {
            throw new MyArraySizeException("Массив другого размера...");
        }
        count = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                try {
                    count += Integer.parseInt(arr[i][j]);
                } catch (Exception e) {
                    throw new MyArrayDataException("Не число в ячейке массива arr[" + i + "][" + j + "]");
                }

            }
        }
    }
}
