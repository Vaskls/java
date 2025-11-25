public class Main {


    public static Person multiplyFields(Person person) {
        if (person instanceof Teacher) {
            Teacher teacher = (Teacher) person;
            teacher.salary *= 5;
            return teacher;
        }else if(person instanceof Student) {
            Student student = (Student) person;
            // student.updateAge();
            student.age *= 2;
            return student;
        }
        return person;
    }

    public static Person[] multiplyFieldMultiple(Person person1, Person person2) {
        Person p1Modified = multiplyFields(person1);
        Person p2Modified = multiplyFields(person2);
        return new Person[]{p1Modified, p2Modified};
    }
    

    public static void main(String[] args) {
 
        Student student1 = new Student(
                "Bob",
                21,
                new int[]{75, 63, 90},
                new String[]{"Chemistry", "Math", "Informatics"}
        );
 
        Teacher teacher1 = new Teacher(
                "Dr. John",
                33,
                12500.00,
                new String[]{"Chemistry", "Informatics"} 
        );
 
        System.out.println("Student info:");
        student1.displayInfo();
        System.out.println();
 
        System.out.println("Teacher info:");
        teacher1.displayInfo();
        
        
        // method that accepts 2 persons
        // if person is a student: age*2
        // if persin is a teacher: salary*5
        multiplyFieldMultiple(student1, teacher1);
        System.out.println("Student info:");
        student1.displayInfo();
        System.out.println();
 
        System.out.println("Teacher info:");
        teacher1.displayInfo();
    }
    
}