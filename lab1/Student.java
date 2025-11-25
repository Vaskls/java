public class Student extends Person {
    private int[] grades;
    private String[] subjects;
 
    public Student(String name, int age, int[] grades, String[] subjects) {
        this.name = name;
        this.age = age;

        this.grades = grades;
        this.subjects = subjects;
    }

    public void updateAge() {
        this.age *= 2;
    }
 
    @Override
    public void displayInfo() {
        System.out.println("Student: " + this.name + ", age: " + this.age + "\n");
        System.out.println("grades: ");
        for (int i = 0; i < grades.length; i++) {
            int grade = grades[i];
            System.out.print(grade + " ");
        } 
        System.out.println("\nsubjects: ");
        for (int i = 0; i < subjects.length; i++) {
            String subject = subjects[i];
            System.out.print(subject + " ");
        } 
        System.out.println();
    }
}
