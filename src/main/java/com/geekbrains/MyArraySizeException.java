package com.geekbrains;

public class MyArraySizeException extends Exception {
    private String message;

    public MyArraySizeException(String s) {
        this.message = s;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
