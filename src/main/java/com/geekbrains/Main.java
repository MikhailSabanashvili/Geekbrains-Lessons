package com.geekbrains;

import java.util.Random;
import java.util.Scanner;

public class Main {
    private final static int SIZE = 5;
    private final static int DOTS_TO_WIN = 2;
    private final static char DOT_EMPTY = '.';
    private final static char DOT_X = 'X';
    private final static char DOT_O = 'O';
    public static char[][] MAP;
    private final static Scanner SCANNER = new Scanner(System.in);
    private final static Random RANDOM = new Random();

    public static void main(String[] args) {
        initMap();
        printMap();
        while (true) {
            humanTurn();
            printMap();
            if (checkWin(DOT_X,0, DOTS_TO_WIN - 1, 0, DOTS_TO_WIN - 1)) {
                System.out.println("Победил человек");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }
            aiTurn();
            printMap();
            if (checkWin(DOT_O, 0, DOTS_TO_WIN - 1, 0, DOTS_TO_WIN - 1)) {
                System.out.println("Победил ИИ");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }
        }
        System.out.println("Игра окончена");
        SCANNER.close();
    }

    //работает на любом поле и любом числе диагоналей в этом поле.
    // Разбивает большое поле на маленькие, в которых длина диагонали = DOTS_TO_WIN
    //Дальше считает по маленькому полю, через рекурсию смещаясь на следующее маленькое поле - пока не найдет условие победы
    //или пока не переберет всё поле
    public static boolean checkWin(char symbol, int beginI, int endI, int beginJ, int endJ) {
        char[][] smallArr = createSmallArray(beginI, endI, beginJ, endJ);
        int columnSize = DOTS_TO_WIN - 1;
        //массивы для проверки, суть - последовательность одинаковых символов должна быть непрерывна
        int[] continuosSequenseDiagonal1 = new int[DOTS_TO_WIN];
        int[] continuosSequenseDiagonal2 = new int[DOTS_TO_WIN];
        int[] continuosSequenseRow = new int[DOTS_TO_WIN];
        int[] continuosSequenseColumn = new int[DOTS_TO_WIN];
        for (int i = 0; i < DOTS_TO_WIN; i++) {
            for (int j = 0; j < DOTS_TO_WIN; j++) {
                if (smallArr[i][j] == symbol)
                    continuosSequenseColumn[j] = 1;

                if (smallArr[j][i] == symbol)
                    continuosSequenseRow[j] = 1;

                if (i == j && smallArr[i][j] == symbol)
                    continuosSequenseDiagonal1[j] = 1;

            }

            if (smallArr[i][columnSize] == symbol)
                continuosSequenseDiagonal2[i] = 1;

            columnSize -= 1;
            editArray(continuosSequenseColumn);
            editArray(continuosSequenseRow);
        }

        boolean result = checkArrays(continuosSequenseDiagonal1) ||
                checkArrays(continuosSequenseDiagonal2) ||
                checkArrays(continuosSequenseRow) ||
                checkArrays(continuosSequenseColumn);

        if (result)
            return true;
        //суть этого проста - мы при следующем шаге рекурсии не должны выйти за пределы большого массива
        if (endI + 1 == SIZE) {
            beginI = 0;
            endI = beginI + DOTS_TO_WIN - 1;
            if (endJ + 1 == SIZE) {
                return false;
            }
            beginJ += 1;
            endJ += 1;
        } else {
            beginI += 1;
            endI += 1;
        }


        return checkWin(symbol, beginI, endI, beginJ, endJ);
    }

    public static char[][] createSmallArray(int beginI, int endI, int beginJ, int endJ) {
        char[][] smallArr = new char[DOTS_TO_WIN][DOTS_TO_WIN];
        //чтобы не сломать логику просчета диагоналей по индексам - надо сделать новое маленькое поле
        for (int i1 = 0, i2 = beginI; i1 < DOTS_TO_WIN && i2 <= endI; i1++, i2++) {
            for (int j1 = 0, j2 = beginJ; j1 < DOTS_TO_WIN && j2 <= endJ; j1++, j2++) {
                smallArr[i1][j1] = MAP[i2][j2];
            }
        }
        return smallArr;
    }

    public static void editArray(int[] array) {
        //чистит массивы обратно в ноль, если строка/столбец пройдены, но победа не достигнута
        int count = 0;
        for (int i = 0; i < DOTS_TO_WIN; i++) {
            if (array[i] == 1)
                count++;
            if (count != array.length)
                for (int j = 0; j < DOTS_TO_WIN; j++) {
                    array[j] = 0;
                }
        }
    }

    public static boolean checkArrays(int[] result) {
        int count = 0;
        for (int i = 0; i < result.length; i++) {
            if (result[i] == 1)
                count++;
            else
                count = 0;

            if (count == DOTS_TO_WIN) {
                return true;
            }
        }
        return false;
    }

    public static void initMap() {
        MAP = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                MAP[i][j] = DOT_EMPTY;
            }
        }
    }

    private static boolean isMapFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (MAP[i][j] == DOT_EMPTY) {
                    return false;
                }
            }
        }

        return true;
    }

    private static void aiTurn() {
        int x;
        int y;

        do {
            x = RANDOM.nextInt(SIZE);
            y = RANDOM.nextInt(SIZE);
        } while (!isCellValid(x, y));
        System.out.println("Компьютер сходил в точку" + (x + 1) + " " + (y + 1));
        MAP[y][x] = DOT_O;
    }

    private static void humanTurn() {
        int x;
        int y;

        do {
            System.out.println("Введите координаты в формате Х и У");
            x = SCANNER.nextInt() - 1;
            y = SCANNER.nextInt() - 1;
        } while (!isCellValid(x, y));
        MAP[y][x] = DOT_X;
    }

    private static boolean isCellValid(int x, int y) {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) {
            return false;
        }

        if (MAP[y][x] == DOT_EMPTY) {
            return true;
        }

        return false;
    }

    private static void printMap() {
        for (int i = 0; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(MAP[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
