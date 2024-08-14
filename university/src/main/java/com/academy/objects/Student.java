package com.academy.objects;

import java.util.ArrayList;

public class Student extends Person {

    private static int idCounter = 0;
    private int id;
    private int age;
    private ArrayList<UniversityClass> studentClasses = new ArrayList<UniversityClass>();

    public Student(String name, int age) {
        super.setName(name);
        this.age = age;
        this.id = ++idCounter;
    }

    public void addClass(UniversityClass universityClass) {
        this.studentClasses.add(universityClass);
    }

    public ArrayList<UniversityClass> getClasses() {
        return studentClasses;
    }

    public ArrayList<UniversityClass> getStudentClasses() {
        return this.studentClasses;
    }



    @Override
    public String toString() {
        return  " Id: " + id +
                " Name: " + super.getName() +
                " age: " + age;
    }

    public int getId() {
        return this.id;
    }
}
