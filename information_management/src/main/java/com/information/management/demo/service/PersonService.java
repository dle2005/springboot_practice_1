package com.information.management.demo.service;

import com.information.management.demo.domain.Block;
import com.information.management.demo.domain.Person;
import com.information.management.demo.repository.BlockRepository;
import com.information.management.demo.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private BlockRepository blockRepository;

    public List<Person> getPeopleExcludeBlocks() {
//        List<Person> people = personRepository.findAll();
//        List<Block> blocks = blockRepository.findAll();
//        List<String> blockNames = blocks.stream().map(Block::getName).collect(Collectors.toList());

//        return people.stream().filter(person -> person.getBlock() == null).collect(Collectors.toList());

        return personRepository.findByBlockIsNull();
    }

    @Transactional(readOnly = true)
    public Person getPerson(Long id) {
        Person person = personRepository.findById(id).get();

        System.out.println("person: " + person);
        log.info("person : {}", person);

        return person;
    }

    public List<Person> getPersonByName(String name) {
//        List<Person> people = personRepository.findAll();
//
//        return people.stream().filter(person -> person.getName().equals(name)).collect(Collectors.toList());

        return personRepository.findByName(name);
    }
}
