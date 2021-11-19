package com.geekbrains;

public class Main {
    public static void main(String[] args) {
        Employee[] employees = {
                new Employee("Ivan", "Ivanov", "Ivanovich", "Engineer",
                "ivivan@mailbox.com", "89158302637", 23000, 23),
                new Employee("Petr", "Ivanov", "Ivanovich", "Engineer",
                        "ivivan@mailbox.com", "89158302637", 23000, 45),
                new Employee("Nick", "Ivanov", "Ivanovich", "Engineer",
                        "ivivan@mailbox.com", "89158302637", 23000, 60),
                new Employee("Vasya", "Ivanov", "Ivanovich", "Engineer",
                        "ivivan@mailbox.com", "89158302637", 23000, 9),
                new Employee("Isua", "Ivanov", "Ivanovich", "Engineer",
                        "ivivan@mailbox.com", "89158302637", 23000, 15)};

        for (Employee employee: employees) {
            if(employee.getAge() > 40)
                System.out.println(employee.toString());
        }

    }
}
