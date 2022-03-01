package com.geekbrains;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main<T> {
    public void task1(T[] arr) {
        if(arr.length < 2)
            return;

        T temp = arr[0];
        arr[0] = arr[1];
        arr[1] = temp;
        System.out.println(Arrays.toString(arr));
    }

    public ArrayList<T> task2(T[] arr) {
        return new ArrayList<>(List.of(arr));
    }

    public static void main(String[] args) {
        Main<Integer> object = new Main<>();
        object.task1(new Integer[]{1, 2});

        ArrayList<Integer> list = object.task2(new Integer[]{1, 2});
        System.out.println(list.toString());

        ArrayList<Apple> list1 = new ArrayList<>();
        list1.add(new Apple());
        list1.add(new Apple());
        list1.add(new Apple());

        ArrayList<Orange> list2 = new ArrayList<>();
        list2.add(new Orange());
        list2.add(new Orange());
        list2.add(new Orange());

        Box<Apple> box1 = new Box<>(list1);
        System.out.println(box1.getWeight());
        Box<Orange> box2 = new Box<>(list2);
        System.out.println(box2.getWeight());

        System.out.println("Сравним коробки: " + box1.compare(box2));

        //пересыпем
        ArrayList<Apple> list3 = new ArrayList<>();
        Box<Apple> box3 = new Box<>(list3);
        list1.add(new Apple());
        list1.add(new Apple());
        list1.add(new Apple());
        box1.fillBox(box3);
        System.out.println(box1.getList().toString());
        System.out.println(box3.getList().toString());

        //положим че нибудь в коробку Путина
        box3.putInBox(new Apple());
        System.out.println(box3.getList().toString());
    }
}
