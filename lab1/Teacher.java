public class Teacher extends Person {
    public double salary;
    private String[] subjects;
    
    public Teacher(String name, int age, double salary, String[] subjects) {
        this.name = name;
        this.age = age;

        this.salary = salary;
        this.subjects = subjects;
    }

    
    @Override
    public void displayInfo() {
        System.out.println("Teacher: " + this.name + ", age " + this.age);
        System.out.println("Salary: " + salary + " $");
        System.out.println("\nsubjects taught:");
        for (int i = 0; i < subjects.length; i++) {
            String subject = subjects[i];
            System.out.print(subject + " ");
        } 
        System.out.println();
    }
}