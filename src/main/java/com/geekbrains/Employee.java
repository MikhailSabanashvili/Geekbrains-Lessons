package com.geekbrains;

public class Employee {
    private final String firstName;
    private final String lastName;
    private final String patronymic;
    private final String position;
    private final String email;
    private final String number;
    private final int salary;
    private final int age;

    public Employee(String firstName, String lastName, String patronymic, String position, String email, String number, int salary, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.position = position;
        this.email = email;
        this.number = number;
        this.salary = salary;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", position='" + position + '\'' +
                ", email='" + email + '\'' +
                ", number='" + number + '\'' +
                ", salary=" + salary +
                ", age=" + age +
                '}';
    }

}
