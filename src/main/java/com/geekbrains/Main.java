package com.geekbrains;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        //задача №1
        ArrayList<String> words = new ArrayList<>();
        words.add("Вася");
        words.add("Петя");
        words.add("Катя");
        words.add("Ната");
        words.add("Вася");
        words.add("Василиса");
        words.add("Вася");
        words.add("Надя");
        words.add("Ната");
        words.add("Злата");
        words.add("Виктор");
        words.add("Катя");

        HashSet<String> set = new HashSet<>(words);
        //считаем дубликаты
        HashMap<String, Integer> dublicateElements = new HashMap<>();
        for (int i = 0; i < words.size(); i++) {
            int count = 0;
            for (int j = i + 1; j < words.size(); j++) {
                if(words.get(i).equals("")){
                    break;
                }

                if(words.get(i).equals(words.get(j))) {
                    count++;
                    words.set(j, "");
                }
            }

            if(count > 0) {
                dublicateElements.put(words.get(i), count + 1);
                System.out.println("Слово " + words.get(i) + " встречается в списке " + (count + 1) + " раз");
            }
        }

        System.out.println("Список слов(исключая повторения): " + set.toString());

        //задача №2
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.addSecondName("Васильевна");
        phoneBook.addPhoneNumberBySecondName("Васильевна", 89101234576L);
        phoneBook.addPhoneNumberBySecondName("Васильевна", 89995687214L);

        System.out.println(phoneBook.getPhoneNumbersBySecondName("Васильевна"));
    }
}
