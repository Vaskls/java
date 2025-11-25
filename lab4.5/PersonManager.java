import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PersonManager {

    public static void savePersonsToText(List<Person> persons, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Person person : persons) {
                writer.write(person.toString()); 
                writer.newLine(); 
            }
            System.out.println("Saved " + persons.size() + " persons to " + filename + " (text).");
        } catch (IOException e) {
            System.err.println("Error saving persons to text file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static List<Person> loadPersonsFromText(String filename) {
        List<Person> loadedPersons = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                try {
                    String[] dataParts = line.split("\\{", 2); 
                    if (dataParts.length != 2) {
                        System.err.println("Skipping malformed line (missing '{'): " + line);
                        continue;
                    }
                    String type = dataParts[0].trim();
                    
                    // Pass only the inner content
                    String innerContent = dataParts[1].replace("}", "").trim();

                    Person person = null;
                    
                    
                    switch (type) {
                        case "Student":
                            person = Student.fromText(innerContent); 
                            break;
                        case "Teacher":
                            person = Teacher.fromText(innerContent);
                            break;
                        default:
                            System.err.println("Unknown person type encountered: '" + type + "' in line: " + line);
                            break;
                    }
                    

                    if (person != null) {
                        loadedPersons.add(person);
                    }
                } catch (Exception e) { 
                    System.err.println("Error parsing line: '" + line + "' - " + e.getMessage());
                    e.printStackTrace();
                }
            }
            System.out.println("Loaded " + loadedPersons.size() + " persons from " + filename + " (text).");
            return loadedPersons;
        } catch (IOException e) {
            System.err.println("Error loading persons from text file: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}