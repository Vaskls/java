
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Person> persons = Arrays.asList(
            new Student(
                "Bob",
                19,
                new int[]{83, 91},
                new String[]{"Chemistry", "English"}
            ),
            
            new Student(
                "John",
                21,
                new int[]{75, 63},
                new String[]{"Math", "Informatics"}
            ),
            
            new Student(
                "Charlie",
                20,
                new int[]{82, 77},
                new String[]{"Chemistry", "Biology"}
            ),
            new Teacher(
                "Dr. John",
                32,
                10000.00,
                new String[]{"Math", "Informatics"} 
            ),
            new Teacher(
                "Dr. Smith",
                41,
                13000.00,
                new String[]{"Chemistry", "Biology"} 
            )
        );
        // for (Person person : persons) {
        //         System.out.println(person.toString());
        // }
        String filename = "persons.txt";
        PersonManager.savePersonsToText(persons, filename);
        List<Person> loadedPersons = PersonManager.loadPersonsFromText(filename);

        if (loadedPersons != null) {
            System.out.println("Loaded persons:");
            for (Person person : loadedPersons) {
                person.displayInfo();
            }
        }

    }
    
}