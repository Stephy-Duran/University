package com.academy.data;

import com.academy.objects.*;

public class Data {

    public static void initializeData(University university) {

        university.getTeachers().add(new FullTimeTeacher("Omar Benitez", 1000, 3));
        university.getTeachers().add(new FullTimeTeacher("Gladys Betancourt", 1200, 7));
        university.getTeachers().add(new PartTimeTeacher("Claudia Barbosa", 100, 25));
        university.getTeachers().add(new PartTimeTeacher("Nelly Ortega", 80, 8));

        university.getStudents().add(new Student("Luli Olaya", 21));
        university.getStudents().add(new Student("Charles Darwin ", 19));
        university.getStudents().add(new Student("Carlos Sanchez", 17));
        university.getStudents().add(new Student("Brahyam Aldana", 24));

        UniversityClass math = new UniversityClass("Math", "A101", university.getTeachers().getFirst());
        UniversityClass internetOfThings = new UniversityClass("IOT Internet of Things", "A102", university.getTeachers().get(1));
        UniversityClass softwareArchitecture = new UniversityClass("Software Architecture", "A105", university.getTeachers().get(2));
        UniversityClass algorithmicThinking = new UniversityClass("Algorithmic thinking ", "A105", university.getTeachers().get(2));

        university.getClasses().add(math);
        university.getClasses().add(internetOfThings);
        university.getClasses().add(softwareArchitecture);
        university.getClasses().add(algorithmicThinking);

        math.addStudent(university.getStudents().getFirst());
        math.addStudent(university.getStudents().get(3));
        internetOfThings.addStudent(university.getStudents().get(1));
        internetOfThings.addStudent(university.getStudents().get(2));
        math.addStudent(university.getStudents().get(2));
        softwareArchitecture.addStudent(university.getStudents().get(2));
        algorithmicThinking.addStudent(university.getStudents().get(2));


    }

}
