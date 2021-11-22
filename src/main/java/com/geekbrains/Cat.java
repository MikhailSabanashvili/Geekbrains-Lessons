package com.geekbrains;

public class Cat extends Animal {
    private static int countCats;

    public Cat(String name) {
        super(name);
        countCats++;
    }

    public static int numberCats() {
        return countCats;
    }

    @Override
    void run(int metres) {
        if(metres > 200)
            System.out.println(this.name + " пробежал/a " + 200 + " метров");
        else
            System.out.println(this.name + " пробежал/a " + metres + " метров");
    }

    @Override
    void swim(int metres) {
        System.out.println(this.name + " не умеет плавать, потому что кот..или кошка");
    }
}
