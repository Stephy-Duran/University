package com.academy.objects;

public class FullTimeTeacher extends Teacher {

    private int yearsOfExperience;

    public FullTimeTeacher(String name, double baseSalary, int yearsOfExperience) {

        this.setName(name);
        this.yearsOfExperience = yearsOfExperience;
        super.setBaseSalary(baseSalary);
    }

    @Override
    public double calculateSalary() {
        return super.getBaseSalary() + (this.yearsOfExperience * 1.10);
    }

    @Override
    public String toString() {
        return  "--------------------------------\n" +
                "Type: Full Time Teacher\n" +
                "Name: " + super.getName() + "\n" +
                "Years of Experience: " + this.yearsOfExperience + "\n" +
                "Salary: $" + String.format("%.2f", this.calculateSalary()) + "\n" +
                "--------------------------------\n";
    }


}
