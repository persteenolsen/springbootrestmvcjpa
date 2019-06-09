package com.persteenolsen.springbootrestmvcjpa.controller;


import com.persteenolsen.springbootrestmvcjpa.model.PersonEntity;

import com.persteenolsen.springbootrestmvcjpa.dao.PersonRepository;
//import com.persteenolsen.springbootrestmvcjpa.service.PersonService;

import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
 
@RestController
@RequestMapping(value = "/person-management", produces = { MediaType.APPLICATION_JSON_VALUE })
public class PersonRestController
{
    @Autowired
    private PersonRepository repository;
 
    public PersonRepository getRepository() {
        return repository;
    }
 
    public void setRepository(PersonRepository repository) {
        this.repository = repository;
    }
 
    @GetMapping(value = "/persons")
    public List<PersonEntity> getAllPersons() {
        // TEST 
	//Getting the runtime reference from system
	//Runtime runtime = Runtime.getRuntime();
    //runtime.gc();
    
     return repository.findAll();
    }
 
    @PostMapping("/person")
    PersonEntity createOrSavePerson(@RequestBody PersonEntity newPerson) {
        return repository.save(newPerson);
    }
 
    @GetMapping("/person/{id}")
    PersonEntity getPersonById(@PathVariable Long id) {
        return repository.findById(id).get();
    }
 
    @PutMapping("/person/{id}")
    PersonEntity updatePerson(@RequestBody PersonEntity newPerson, @PathVariable Long id) {
 
        return repository.findById(id).map(person -> {
            person.setName(newPerson.getName());
            person.setEmail(newPerson.getEmail());
            person.setAge(newPerson.getAge());

            return repository.save(person);
        }).orElseGet(() -> {
            newPerson.setId(id);
            return repository.save(newPerson);
        });
    }
 
    @DeleteMapping("/person/{id}")
    void deletePerson(@PathVariable Long id) {
        repository.deleteById(id);
    }
}