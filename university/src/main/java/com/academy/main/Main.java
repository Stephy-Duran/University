package com.academy.main;

import com.academy.data.Data;
import com.academy.objects.Student;
import com.academy.objects.Teacher;
import com.academy.objects.University;
import com.academy.objects.UniversityClass;

import java.util.*;


public class Main {

    public static void main(String[] args) {

        University university = new University();
        Data.initializeData(university);
        Scanner scan = new Scanner(System.in);

        showMenu(university, scan);


    }


    public static void showAvailableClasses(List<UniversityClass> classes) {
        System.out.println("Available Classes");
        for (int i = 0; i < classes.size(); i++) {
            UniversityClass className = classes.get(i);
            System.out.println((i + 1) + ". " + className.getName());
        }

    }


    public static int getClassIndexFromUser(Scanner scan) {
        System.out.println("Enter class index:");
        int option = scan.nextInt();
        scan.nextLine();
        return option;
    }

    public static void showOptions() {
        System.out.println("Menu:");
        System.out.println("1. Print all professors");
        System.out.println("2. Print all classes");
        System.out.println("3. Create a new student and add to a class");
        System.out.println("4. Create a new class");
        System.out.println("5. List all classes for a student");
        System.out.println("6. Exit");
    }

    public static void showMenu(University university, Scanner scanner) {
        int option = 0;
        do {
            showOptions();
            System.out.println("ENTER AN OPTION NUMBER: ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.println(university.getTeachers());
                    break;
                case 2:
                    showAvailableClasses(university.getClasses());
                    int index = getClassIndexFromUser(scanner);
                    System.out.println(university.getClasses().get(index - 1).getName().toUpperCase());
                    System.out.println(university.getClasses().get(index - 1));
                    break;
                case 3:
                    System.out.println("Enter the Student's Name");
                    String name = scanner.nextLine();
                    System.out.println("Enter the Student's age");
                    int age = scanner.nextInt();
                    scanner.nextLine();
                    Student student = new Student(name, age);
                    showAvailableClasses(university.getClasses());
                    int opt = getClassIndexFromUser(scanner);
                    university.getClasses().get(opt - 1).addStudent(student);

                    break;
                case 4:
                    createNewSClas(scanner, university);
                    break;
                case 5:
                    showStudentsClasses(university.getStudents(), scanner);
                    break;
                case 6:
                    System.out.println("Goodbye");
                    break;
                default:
                    System.out.println("Enter a valid option");
            }

        } while (option != 6);
    }

    public static void createNewSClas(Scanner scanner, University university) {
        System.out.println("Enter the class name:");
        String className = scanner.nextLine();
        System.out.println("Enter the classroom:");
        String classroom = scanner.nextLine();
        showAvailableTeachers(university);
        System.out.println("Enter teacher's name:");
        String teacherName = scanner.nextLine();

        Teacher selectedTeacher = null;

        for (Teacher teacher1 : university.getTeachers()) {
            if (teacher1.getName().toLowerCase().contains(teacherName.toLowerCase())) {
                selectedTeacher = teacher1;
                break;
            }
        }

        if (selectedTeacher != null) {
            UniversityClass uClass = new UniversityClass(className, classroom, selectedTeacher);
            String option;
            List<Student> studentsToEnter = new ArrayList<>();

            System.out.println("Current students:");
            university.getStudents().forEach(student -> System.out.println("ID: " + student.getId() + ", Name: " + student.getName()));

            System.out.println("Enter the ID number to add the student, press Enter after each ID:");
            System.out.println("Enter 'Q', when you finish");


            do {
                option = scanner.nextLine();
                if (!option.equalsIgnoreCase("Q")) {
                    try {
                        int studentId = Integer.parseInt(option);
                        Student student = findStudentById(university.getStudents(), studentId);
                        if (student != null) {
                            studentsToEnter.add(student);
                            System.out.println(student.getName() + "has been added.");

                        } else {
                            System.out.println("No student found with ID: " + studentId);
                        }

                    } catch (NullPointerException e) {
                        System.out.println("Invalid input. Please enter a valid ID or 'Q' to quit.");
                    }
                }

            } while (!option.equalsIgnoreCase("q"));

            for (Student student : studentsToEnter) {
                uClass.addStudent(student);
            }

            //Add universityClass to the available classes
            university.addUniversityClass(uClass);

            System.out.println("Class successfully created with the following students:");
            studentsToEnter.forEach(student -> System.out.println(student.getName()));

        } else {
            System.out.println("No teacher matches that name.");
        }

    }

    public static Student findStudentById(List<Student> students, int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    public static void showAvailableTeachers(University university) {
        university.getTeachers().forEach(teacher -> System.out.println("Name: " + teacher.getName()));
    }

    public static void showStudentsClasses(List<Student> students, Scanner scanner) {

        System.out.println("Current Students:");
        students.forEach(student -> System.out.println("ID: " + student.getId() + ", Name: " + student.getName()));

        System.out.println("Enter the Student's ID");
        int id = scanner.nextInt();
        scanner.nextLine();

        Student selectedStudent = null;


        for (Student student : students) {
            if (student.getId() == id) {
                selectedStudent = student;
                break;
            }
        }

        if (selectedStudent == null) {
            System.out.println("Student not found.");
        }

        System.out.println(selectedStudent.getName() + " is enrolled in the following classes:");
        for (UniversityClass universityClass : selectedStudent.getClasses()) {
            System.out.println("Class Name: " + universityClass.getName());
            System.out.println("Teacher: " + universityClass.getTeacher().getName());
            System.out.println("Assigned Classroom: " + universityClass.getAssignedClassroom() + "\n");
        }


    }


}
