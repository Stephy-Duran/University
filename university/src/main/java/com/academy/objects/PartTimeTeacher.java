package com.academy.objects;

public class PartTimeTeacher extends Teacher {

    private int hoursPerWeek;

    public PartTimeTeacher(String name, double baseSalary, int hoursPerWeek) {
        super.setName(name);
        this.hoursPerWeek = hoursPerWeek;
        super.setBaseSalary(baseSalary);
    }

    @Override
    public double calculateSalary() {
        return super.getBaseSalary() * this.hoursPerWeek;
    }

    public int sum() {
        return 1 + 2;
    }

    @Override
    public String toString() {
        return "--------------------------------\n" +
                "Type: Part Time Teacher\n" +
                "Name: " + super.getName() + "\n" +
                "Hours per Week: " + this.hoursPerWeek + "\n" +
                "Salary: $" + String.format("%.2f", this.calculateSalary()) + "\n" +
                "--------------------------------\n";
    }
}
