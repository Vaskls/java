package com.example.app.model;

import java.util.Arrays;

public class Student extends Person {

    private int[] grades;
    private String[] subjects;

    public Student() {
        super();
    }

    public Student(String name, int age, int[] grades, String[] subjects) {
        super(name, age);
        this.grades = grades;
        this.subjects = subjects;
    }

    public double getAverageGrade() {
        if (grades == null || grades.length == 0) {
            return 0.0;
        }
        double sum = 0;
        for (int i = 0; i < grades.length; i++) {
            int grade = grades[i];
            sum += grade;
        }
        return sum / grades.length;
    }

    public int[] getGrades() {
        return grades;
    }

    public void setGrades(int[] grades) {
        this.grades = grades;
    }

    public String[] getSubjects() {
        return subjects;
    }

    public void setSubjects(String[] subjects) {
        this.subjects = subjects;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + getId() + "," +
                "name='" + name + '\'' + "," + 
                "age=" + age + "," +
                "grades=" + Arrays.toString(grades) +
                '}';
    }
}
