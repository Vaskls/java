import java.util.Arrays;
import java.util.stream.Collectors;

public class Student extends Person {
    private int[] grades;
    private String[] subjects;
 
    public Student(String name, int age, int[] grades, String[] subjects) {
        this.name = name;
        this.age = age;

        this.grades = grades;
        this.subjects = subjects;
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
    @Override
    public String toString() {
        String quotedSubjects = Arrays.stream(this.subjects)
            .collect(Collectors.joining(";", "[", "]"));
        return "Student{" +
               "name=" + getName() + 
               "|age=" + getAge() +
               "|grades=" + Arrays.toString(this.grades) +  
               "|subjects=" + quotedSubjects +
               '}';
    }

    public static Student fromText(String text) {
        String name = null;
        int age = 0;
        int[] grades = new int[0];
        String[] subjects = new String[0];

        String[] parts = text.split("\\|");

        for (String part : parts) {
            String[] kv = part.split("=", 2); 
            if (kv.length != 2) {
                System.err.println("Warning: Skipping malformed key-value pair in Student data: " + part);
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
                    case "grades":
                        if (value.startsWith("[") && value.endsWith("]")) {
                            String gradesData = value.substring(1, value.length() - 1).trim();
                            if (!gradesData.isEmpty()) {
                                grades = Arrays.stream(gradesData.split(", "))
                                               .map(String::trim)
                                               .mapToInt(Integer::parseInt)
                                               .toArray();
                            }
                        } else {
                             throw new IllegalArgumentException("Malformed grades format: " + value);
                        }
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
                        System.err.println("Warning: Unknown field in Student data: " + key);
                        break;
                }
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Malformed number format for field '" + key + "': " + value, e);
            }
        }

        if (name == null) {
            throw new IllegalArgumentException("Missing 'name' field in Student data: " + text);
        }
        return new Student(name, age, grades, subjects);
    }
}
