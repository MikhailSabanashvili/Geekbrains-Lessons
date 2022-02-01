package com.geekbrains;

import java.util.ArrayList;
import java.util.HashMap;

public class PhoneBook {
    private HashMap<String, ArrayList<Long>> map;

    public PhoneBook() {
        this.map = new HashMap<>();
    }

    public void addSecondName(String secondName) {
        map.put(secondName, new ArrayList<>());
    }

    public void addPhoneNumberBySecondName(String secondName, Long phoneNumber) {
        if(map.containsKey(secondName)) {
            map.get(secondName).add(phoneNumber);
        } else {
            System.out.println("В телефонном справочнике нет такой фамилии");
        }
    }

    public ArrayList<Long> getPhoneNumbersBySecondName(String secondName) {
        return map.get(secondName);
    }
}
