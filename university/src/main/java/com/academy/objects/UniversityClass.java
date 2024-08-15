package com.academy.objects;

import java.util.ArrayList;
import java.lang.StringBuilder;


public class UniversityClass {
    private String name;
    private String assignedClassroom;
    private Teacher teacher;
    private ArrayList<Student> students = new ArrayList<>();

    public UniversityClass(String name, String assignedClassroom, Teacher teacher) {
        this.name = name;
        this.assignedClassroom = assignedClassroom;
        this.teacher = teacher;
        this.students = students;
    }

    public String getName() {
        return this.name;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public String getAssignedClassroom() {
        return assignedClassroom;
    }


    public void addStudent(Student student) {
        this.students.add(student);
        student.addClass(this);
    }

    public String showClassesData() {
        return "--------------------------------\n" +
                "Class Name: '" + this.name + '\'' +
                "ssignedClassroom: '" + this.assignedClassroom + '\'' +
                "\nTeacher: " + this.teacher.getName() +
                "--------------------------------\n";
    }


    @Override
    public String toString() {
        StringBuilder studentsInfo = new StringBuilder();
        for (Student student : students) {
            studentsInfo.append(student.toString()).append("\n");
        }

        return "--------------------------------\n" +
                "{" +
                "Class Name: '" + name + '\'' +
                "\n AssignedClassroom: '" + assignedClassroom + '\'' +
                "\n Teacher: " + teacher.getName() +
                "\n Students: " + students.size() + "\n" + studentsInfo.toString() +
                '}' + "\n" +
                "--------------------------------\n";
    }


}
