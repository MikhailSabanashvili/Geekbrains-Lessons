package com.geekbrains;


import com.geekbrains.annotations.AfterSuite;
import com.geekbrains.annotations.BeforeSuite;
import com.geekbrains.annotations.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;

public class TestCore {
    public static void start(Class<?> tClass) {
        Method[] methods = tClass.getDeclaredMethods();
        Example example = new Example();

        long countBeforeSuite = Arrays.stream(methods)
                .filter(method -> method.getAnnotation(BeforeSuite.class) != null).count();

        long countAfterSuite = Arrays.stream(methods)
                .filter(method -> method.getAnnotation(AfterSuite.class) != null).count();

        if(countBeforeSuite > 1 || countAfterSuite > 1)
            throw new RuntimeException("Number BeforeSuite or AfterSuite annotated methods is more than one");

        Arrays.stream(methods)
                .filter(method -> method.getAnnotation(BeforeSuite.class) != null)
                .forEach(method -> {
                    try {
                        method.invoke(example);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                });

        Comparator<Method> comparator = (o1, o2) -> {
            if(o1.getAnnotation(Test.class).value() > o2.getAnnotation(Test.class).value())
                return 1;
            else if(o1.getAnnotation(Test.class).value() < o1.getAnnotation(Test.class).value())
                return -1;
            else
                return 0;
        };

        Arrays.stream(methods)
                .filter(method -> method.getAnnotation(Test.class) != null)
                .sorted(comparator)
                .forEach(method -> {
                    try {
                        method.invoke(example);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                });

        Arrays.stream(methods)
                .filter(method -> method.getAnnotation(AfterSuite.class) != null)
                .forEach(method -> {
                    try {
                        method.invoke(example);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                });

    }
}
