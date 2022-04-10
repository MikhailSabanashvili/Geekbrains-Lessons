package com.geekbrains;


import com.geekbrains.annotations.AfterSuite;
import com.geekbrains.annotations.BeforeSuite;
import com.geekbrains.annotations.Test;

public class Example {
    @BeforeSuite
    public void prepare(){
        System.out.println("Вызвался метод аннотированный @BeforeSuite");
    }

    @Test(1)
    public void test1(){
        System.out.println("Вызвался метод аннотированный @Test с приоритетом 1");
    }

    @Test(3)
    public void test2(){
        System.out.println("Вызвался метод аннотированный @Test с приоритетом 3");
    }

    @Test(2)
    public void test3(){
        System.out.println("Вызвался метод аннотированный @Test с приоритетом 2");
    }

    @AfterSuite
    public void end(){
        System.out.println("Вызвался метод аннотированный @AfterSuite");
    }
}
