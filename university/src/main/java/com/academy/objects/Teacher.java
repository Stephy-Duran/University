package com.academy.objects;

public abstract class Teacher extends Person {
    private double baseSalary;
    private double salary;

    public abstract double calculateSalary();

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setBaseSalary(double baseSalary){
        this.baseSalary = baseSalary;
    }

    public double getSalary() {
        return salary;
    }

    public double getBaseSalary() {
        return baseSalary;
    }
}
