package com.example.app.web;

import com.example.app.model.Person;
import com.example.app.repo.InMemoryPersonRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/persons")
public class PersonController {

    private final InMemoryPersonRepository repository;

    public PersonController(InMemoryPersonRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Person> getAllPersons() {
        return repository.findAll();
    }

    @PostMapping
    public ResponseEntity<Person> addPerson(@RequestBody Person person) {
        Person saved = repository.save(person);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        boolean removed = repository.deleteById(id);
        if (!removed) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
