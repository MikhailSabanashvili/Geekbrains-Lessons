package com.geekbrains;

/***
 * На самом деле, ощущение что написал фигню. С одной стороны, статическая переменная для счета объектов - это понятно
 * Но методы number...() которые, фактически, геттеры к статической переменной, выглядит ужасно
 * Но иначе эту переменную можно изменить извне, что плохо
 * Хотя и имя то тоже можно изменить извне. Но у каждого животного у нас есть имя, а поставить private = запретить наследование
 * Посоветуй пожалуйста, как можно сделать лучше?
 */
public class Main {
    public static void main(String[] args) {
        Cat cat1 = new Cat("Тиамат");
        Cat cat2 = new Cat("Ньярлатхотеп");

        Dog dog1 = new Dog("Федор");
        Dog dog2 = new Dog("Граф");

        cat1.run(500);
        cat1.run(50);

        dog1.run(1000);
        dog1.run(2);

        cat2.swim(2);
        dog2.swim(30);

        System.out.println("Всего животных: " + Animal.numberAnimals());
        System.out.println("Всего котов: " + Cat.numberCats());
        System.out.println("Всего собак: " + Dog.numberDogs());
    }
}
