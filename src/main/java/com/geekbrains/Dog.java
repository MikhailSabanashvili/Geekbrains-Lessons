package com.geekbrains;

public class Dog extends Animal {
    private static int countDogs;

    public Dog(String name) {
        super(name);
        countDogs++;
    }

    public static int numberDogs() {
        return countDogs;
    }

    @Override
    void run(int metres) {
        if(metres > 500)
            System.out.println(this.name + " пробежал/a " + 500 + " метров");
        else
            System.out.println(this.name + " пробежал/a " + metres + " метров");
    }

    @Override
    void swim(int metres) {
        if (metres > 10)
            System.out.println(this.name + " проплыл/a " + 10 + " метров");
        else
            System.out.println(this.name + " проплыл/a " + metres + " метров");
    }
}
