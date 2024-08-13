package com.academy.objects;

public class FullTimeTeacher extends Teacher {

    private int yearsOfExperience;

    public FullTimeTeacher(double baseSalary, int yearsOfExperience ){

        this.yearsOfExperience = yearsOfExperience;
        super.setBaseSalary(baseSalary);
        super.setSalary(this.calculateSalary());

    }

    @Override
    public double calculateSalary() {
        return super.getBaseSalary() + (yearsOfExperience * 1.10);
    }

}
