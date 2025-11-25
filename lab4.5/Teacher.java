import java.util.Arrays;
import java.util.stream.Collectors;


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
    @Override
    public String toString() {
        String quotedSubjects = Arrays.stream(this.subjects)
            .collect(Collectors.joining(";", "[", "]"));
        return "Teacher{" +
               "name=" + getName() +
               "|age=" + getAge() +
               "|salary=" + this.salary +  
               "|subjects=" + quotedSubjects +
               '}';
    }
    public static Teacher fromText(String text) {
        String name = null;
        int age = 0;
        double salary = 0.0;
        String[] subjects = new String[0];

        String[] parts = text.split("\\|");

        for (String part : parts) {
            String[] kv = part.split("=", 2);
            if (kv.length != 2) {
                System.err.println("Warning: Skipping malformed key-value pair in Teacher data: " + part);
                continue;
            }
            String key = kv[0].trim();
            String value = kv[1].trim();

            try {
                switch (key) {
                    case "name":
                        name = value;
                        break;
                    case "age":
                        age = Integer.parseInt(value);
                        break;
                    case "salary":
                        salary = Double.parseDouble(value);
                        break;
                    case "subjects":
                        if (value.startsWith("[") && value.endsWith("]")) {
                            String subjectsData = value.substring(1, value.length() - 1).trim();
                            if (!subjectsData.isEmpty()) {
                                subjects = subjectsData.split(";");
                            }
                        } else {
                            throw new IllegalArgumentException("Malformed subjects format: " + value);
                        }
                        break;
                    default:
                        System.err.println("Warning: Unknown field in Teacher data: " + key);
                        break;
                }
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Malformed number format for field '" + key + "': " + value, e);
            }
        }

        if (name == null) {
            throw new IllegalArgumentException("Missing 'name' field in Teacher data: " + text);
        }
        return new Teacher(name, age, salary, subjects);
    }



}