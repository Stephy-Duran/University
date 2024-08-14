package com.academy.objects;

import java.util.ArrayList;
import java.util.List;


public class University {

    private List<Teacher> teachers = new ArrayList<>();
    private List<Student> students = new ArrayList<>();
    private List<UniversityClass> classes = new ArrayList<>();

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<UniversityClass> getClasses() {
        return classes;
    }

    public void addUniversityClass(UniversityClass uClass) {
        this.classes.add(uClass);
    }
}
