package com.geekbrains;

public class Main {
    public static void main(String[] args) {
        Cat cat0 = new Cat("Barsik", 5);
        Cat cat1 = new Cat("Murzik", 10);
        Cat cat2 = new Cat("Vaska", 3);

        Plate plate = new Plate(15);

        Cat[] cats = {cat0, cat1, cat2};
        for (Cat cat: cats) {
            cat.eat(plate);
            System.out.println("Кот сытый: " + cat.isSatiety());
        }

        plate.addFood(3);
        cat2.eat(plate);
        System.out.println("Кот сытый: " + cat2.isSatiety());

    }
}
