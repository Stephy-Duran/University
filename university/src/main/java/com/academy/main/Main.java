package com.academy.main;

import com.academy.data.Data;
import com.academy.objects.Student;
import com.academy.objects.Teacher;
import com.academy.objects.University;
import com.academy.objects.UniversityClass;

import javax.swing.*;
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

        while (true) {
            System.out.println("Enter class index:");
            try {
                int option = scan.nextInt();
                scan.nextLine();
                return option;
            } catch (InputMismatchException e) {
                System.out.println("Invalid index class. Please try again and enter a valid number");
                scan.nextLine();
            }
        }
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
                    handleShowClassesAndDetails(university, scanner);
                    break;
                case 3:
                    handleAddStudent(university, scanner);
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

    public static int getValidOption(Scanner scanner) {
        int option = 0;
        boolean validInput = false;

        while (!validInput) {
            System.out.println("ENTER AN OPTION NUMBER: ");
            try {
                option = scanner.nextInt();
                scanner.nextLine();
                if (option >= 1 && option <= 6) {
                    validInput = true;
                } else {
                    System.out.println("Please enter a valid number option.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid option. Please enter a valid number.");
                scanner.nextLine();
            }
        }

        return option;

    }

    public static String getValidStudentName(Scanner scanner) {
        String name;
        while (true) {
            System.out.println("Enter the Student's Full Name:");
            name = scanner.nextLine();
            if (validateNameFormat(name)) {
                return name;
            } else {
                System.out.println("Invalid name. Please enter a name without numbers.");
            }
        }
    }

    public static boolean validateNameFormat(String name) {
        return name.matches("[a-zA-Z ]+");
    }

    public static int getValidAge(Scanner scanner) {
        int age;
        while (true) {
            System.out.println("Enter the Student's age:");
            try {
                age = scanner.nextInt();
                scanner.nextLine();
                return age;
            } catch (InputMismatchException e) {
                System.out.println("Invalid age. Please try again.");
                scanner.nextLine();
            }
        }
    }

    public static void handleShowClassesAndDetails(University university, Scanner scanner) {
        showAvailableClasses(university.getClasses());
        int index = getClassIndexFromUser(scanner);

        if (index >= 1 && index <= university.getClasses().size()) {
            System.out.println(university.getClasses().get(index - 1).getName().toUpperCase());
            System.out.println(university.getClasses().get(index - 1));
        } else {
            System.out.println("Index entered is not available. Please enter a valid index.");
        }
    }

    public static void handleAddStudent(University university, Scanner scanner) {
        String name = getValidStudentName(scanner);
        int age = getValidAge(scanner);
        Student student = new Student(name, age);

        int index;
        boolean validOption = false;
        while (!validOption) {
            showAvailableClasses(university.getClasses());
            index = getClassIndexFromUser(scanner);

            if (index >= 1 && index <= university.getClasses().size()) {
                university.getClasses().get(index - 1).addStudent(student);
                university.getStudents().add(student);
                System.out.println(student.getName() + "has been added successfully.");
                showCurrentStudents(university);
                validOption = true;
            } else {
                System.out.println("Index entered is not available. Please enter a valid index");
            }
        }

    }


    public static void createNewSClas(Scanner scanner, University university) {
        String className = getValidClassName(scanner);
        String classroom = getClassroomName(scanner);
        showAvailableTeachers(university);
        System.out.println("Enter teacher's name:");
        String teacherName = getValidTeacherName(scanner);

        Teacher selectedTeacher = getTeacherByName(university.getTeachers(), teacherName);


        if (selectedTeacher != null) {
            UniversityClass uClass = new UniversityClass(className, classroom, selectedTeacher);
            String option;
            List<Student> studentsToEnter = new ArrayList<>();

            showCurrentStudents(university);

            do {
                System.out.println("Enter the ID number to add a student, then press Enter or write 'exit' to finish.");
                option = scanner.nextLine();
                if (!option.equalsIgnoreCase("exit")) {
                    try {
                        int studentId = Integer.parseInt(option);
                        Student student = findStudentById(university.getStudents(), studentId);
                        if (student != null && !isStudentIdInList(studentsToEnter, student.getId())) {
                            studentsToEnter.add(student);
                            System.out.println(student.getName() + " has been added.");

                        } else {
                            System.out.println("The ID must be an ID from the student list and must not be repeated.");
                        }

                    } catch (NullPointerException | NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid ID or 'exit' to quit.");
                    }
                }

            } while (!option.equalsIgnoreCase("exit"));

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

    private static boolean isStudentIdInList(List<Student> studentList, int id) {
        for (Student s : studentList) {
            if (s.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public static Teacher getTeacherByName(List<Teacher> teachers, String name) {
        for (Teacher teacher1 : teachers) {
            if (teacher1.getName().toLowerCase().contains(name.toLowerCase())) {
                return teacher1;
            }
        }
        return null;
    }

    public static String getValidClassName(Scanner scanner) {
        String className;
        while (true) {
            System.out.println("Enter the class name:");
            className = scanner.nextLine();
            if (validateNameFormat(className)) {
                return className;
            } else {
                System.out.println("Invalid class name.");
            }
        }
    }

    public static String getClassroomName(Scanner scanner) {
        String classroom;
        System.out.println("Enter the classroom name:");
        classroom = scanner.nextLine();
        return classroom;
    }

    public static String getValidTeacherName(Scanner scanner) {
        String teacherName;
        while (true) {
            teacherName = scanner.nextLine();
            if (validateNameFormat(teacherName)) {
                return teacherName;
            } else {
                System.out.println("Invalid teacher's name.");
            }
        }
    }


    private static void showCurrentStudents(University university) {
        System.out.println("Current Students:");
        university.getStudents().forEach(student -> System.out.println("ID: " + student.getId() + ", Name: " + student.getName()));
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
        System.out.println("Current teachers:");
        university.getTeachers().forEach(teacher -> System.out.println("Name: " + teacher.getName()));
    }

    public static void showStudentsClasses(List<Student> students, Scanner scanner) {

        System.out.println("Current Students:");
        students.forEach(student -> System.out.println("ID: " + student.getId() + ", Name: " + student.getName()));

        int id = -1;

        while (id < 0) {
            System.out.println("Enter the Student's ID:");
            try {
                id = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid ID. Please enter a valid number.");
                scanner.nextLine();
            }
        }

        Student selectedStudent = findStudentById(students, id);

        if (selectedStudent == null) {
            System.out.println("Student not found.");
        } else {
            System.out.println(selectedStudent.getName() + " is enrolled in the following classes:");
            for (UniversityClass universityClass : selectedStudent.getClasses()) {
                System.out.println("Class Name: " + universityClass.getName());
                System.out.println("Teacher: " + universityClass.getTeacher().getName());
                System.out.println("Assigned Classroom: " + universityClass.getAssignedClassroom() + "\n");
            }
        }


    }


}
