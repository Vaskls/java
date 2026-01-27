package com.example.app.model;

import java.util.Arrays;

public class Teacher extends Person {

    private double salary;
    private String[] subjects;

    public Teacher() {
        super();
    }

    public Teacher(String name, int age, double salary, String[] subjects) {
        super(name, age);
        this.salary = salary;
        this.subjects = subjects;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String[] getSubjects() {
        return subjects;
    }

    public void setSubjects(String[] subjects) {
        this.subjects = subjects;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + getId() + "," +
                "name='" + name + '\'' + "," +
                "age=" + age + "," +
                "salary=" + salary + "," +
                "subjects=" + Arrays.toString(subjects) +
                '}';
    }
}
