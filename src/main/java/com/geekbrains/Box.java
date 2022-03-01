package com.geekbrains;

import java.util.ArrayList;

public class Box<T> {
    private ArrayList<T> list;

    public Box(ArrayList<T> list) {
        this.list = list;
    }

    public ArrayList<T> getList() {
        return list;
    }

    public float getWeight() {
        if (list.get(0) instanceof Apple)
            return list.size() * Apple.getWeight();
        else
            return list.size() * Orange.getWeight();
    }

    public boolean compare(Box<?> box) {
        return this.getWeight() == box.getWeight();
    }

    public void fillBox(Box<?> box) {
        if(list.size() == 0 || box.getList().size() == 0)
            return;

        //таким ужасным, неказистым способом я сравнил классы объектов, лежащих в листах одной коробки и другой)))
        if(!(list.get(0).getClass().toString().equals(box.getList().get(0).getClass().toString())))
            return;

        //как бы получше это обработать?
        list.addAll((ArrayList<T>) box.getList());
        box.getList().clear();
    }

    public void putInBox(T fruct) {
        list.add(fruct);
    }
}
