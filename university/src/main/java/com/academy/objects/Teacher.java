package com.academy.objects;

public abstract class Teacher extends Person {
    private double baseSalary;

    public abstract double calculateSalary();

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

}
