package com.example.app.repo;

import com.example.app.model.Person;
import com.example.app.model.Student;
import com.example.app.model.Teacher;
import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryPersonRepository {

    private final Map<Long, Person> persons = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    @PostConstruct
    public void init() {
        Student st1 = new Student("Olivia", 16, new int[]{98, 97, 99}, new String[]{"Advanced Math", "Chemistry"});
        Student st2 = new Student("Noah", 20, new int[]{70, 65, 72}, new String[]{"Art History", "Music Theory"});
        Student st3 = new Student("Emma", 18, new int[]{80, 85, 82}, new String[]{"Biology", "Psychology"});
        Student st4 = new Student("James", 19, new int[]{92, 88, 95}, new String[]{"Software Engineering", "Data Structures"});
        Student st5 = new Student("Mia", 22, new int[]{75, 80, 70}, new String[]{"Marketing", "Business Ethics"});


        Teacher th1 = new Teacher("Prof. Richard Stone", 58, 6800.00, new String[]{"Astrophysics", "Cosmology"});
        Teacher th2 = new Teacher("Mr. David Chen", 32, 4200.00, new String[]{"Calculus", "Statistics"});
        Teacher th3 = new Teacher("Ms. Sarah Miller", 40, 4900.00, new String[]{"World History", "Political Science"});

        save(st1);
        save(st2);
        save(st3);
        save(st4);
        save(st5);
        save(th1);
        save(th2);
        save(th3);
    }

    public List<Person> findAll() {
        return new ArrayList<>(persons.values());
    }

    public Optional<Person> findById(Long id) {
        return Optional.ofNullable(persons.get(id));
    }

    public Person save(Person person) {
        if (person.getId() == null) {
            person.setId(idGenerator.getAndIncrement());
        }
        persons.put(person.getId(), person);
        return person;
    }

    public boolean deleteById(Long id) {
        return persons.remove(id) != null;
    }
}
