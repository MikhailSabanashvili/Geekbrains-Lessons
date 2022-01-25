package com.geekbrains;

public class MyArrayDataException extends Exception {
    private String message;

    public MyArrayDataException(String s){
        this.message = s;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
