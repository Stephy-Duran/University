package com.academy.objects;

public class PartTimeTeacher extends Teacher {

    private int hoursPerWeek;

    public PartTimeTeacher(double baseSalary, int hoursPerWeek) {
        this.hoursPerWeek = hoursPerWeek;
        super.setBaseSalary(baseSalary);
        super.setSalary(calculateSalary());
    }

    @Override
    public double calculateSalary() {
        return super.getBaseSalary() * hoursPerWeek;
    }
}
