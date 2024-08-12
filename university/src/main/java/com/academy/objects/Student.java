package com.academy.objects;

public class Student extends Person {

    private static int idCounter = 0;
    private int id;
    private int age;

    public Student(String name, int age) {
        super.setName(name);
        this.age = age;
        this.id = ++idCounter;
    }

}
