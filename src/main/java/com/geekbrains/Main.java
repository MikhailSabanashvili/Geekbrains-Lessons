package com.geekbrains;

public class Main {
    public static void main(String[] args) {
        System.out.println(isLeapYear(100));
        System.out.println(checkSum(10, 1));
        checkPositive(2);
        System.out.println(checkNegative(-1));
        printString("Хочу видеть код Роберта Мартина - все его методы должны быть не больше трех строк кода", 3);
    }

    public static boolean checkSum(int number1, int number2) {
        return (number1 + number2) <= 20 && (number1 + number2) > 10;
    }

    public static void checkPositive(int number) {
        if (number >= 0)
            System.out.println("Число положительное");
        else
            System.out.println("Число отрицательное");
    }

    public static boolean checkNegative(int number) {
        return number < 0;
    }

    public static void printString(String someString, int count) {
        for (int i = 0; i < count; i++) {
            System.out.println(someString);
        }
    }

    public static boolean isLeapYear(int year) {
        if (year % 400 == 0) {
            return true;
        } else if (year % 4 == 0) {
            if (year % 100 == 0) {
                return false;
            }
            return true;
        } else
            return false;

    }
}
