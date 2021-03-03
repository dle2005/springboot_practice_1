package com.information.management.demo.service;

import com.information.management.demo.domain.Block;
import com.information.management.demo.domain.Person;
import com.information.management.demo.domain.dto.Birthday;
import com.information.management.demo.repository.BlockRepository;
import com.information.management.demo.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonServiceTest {

    @Autowired
    private PersonService personService;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private BlockRepository blockRepository;

    @Test
    void getPeopleExcludeBlocks() {
        List<Person> result = personService.getPeopleExcludeBlocks();

//        System.out.println(result);
        result.forEach(System.out::println);
    }

    @Test
    void getPeopleByName() {
        List<Person> result = personService.getPersonByName("martin");

        result.forEach(System.out::println);
    }

    @Test
    void findByBirthdayBetween() {
        givenPerson("martin", 10, LocalDate.of(1992, 3, 4));
        givenPerson("david", 9, LocalDate.of(1991, 8, 15));
        givenPerson("dennis", 7, LocalDate.of(2000, 3, 4));
        givenPerson("martin", 11, LocalDate.of(1997, 1, 29));

        List<Person> result = personRepository.findByMonthOfBirthday(8, 15);

        result.forEach(System.out::println);
    }

    @Test
    void cascadeTest() {
        givenPeople();

        List<Person> result = personRepository.findAll();
        result.forEach(System.out::println);

        Person person = result.get(3);
        person.getBlock().setStartDate(LocalDate.now());
        person.getBlock().setEndDate(LocalDate.now());

        personRepository.save(person);
        personRepository.findAll().forEach(System.out::println);

//        personRepository.delete(person);
//        personRepository.findAll().forEach(System.out::println);
//        blockRepository.findAll().forEach(System.out::println);

        person.setBlock(null);
        personRepository.save(person);
        personRepository.findAll().forEach(System.out::println);
        blockRepository.findAll().forEach(System.out::println);
    }

    @Test
    void getPerson() {
        givenPeople();

        Person person = personService.getPerson(3L);

        System.out.println(person);
    }

    private void givenBlockPerson(String name, int age) {
        Person blockPerson = new Person(name, age);
        blockPerson.setBlock(new Block(name));

        personRepository.save(blockPerson);
    }

    private void givenPeople() {
        givenPerson("martin", 10);
        givenPerson("david", 9);
        givenPerson("dennis", 7);
        givenBlockPerson("martin", 11);
    }

    private void givenPerson(String name, int age, LocalDate birthday) {
        Person person = new Person(name, age);
        person.setBirthday(new Birthday(birthday));
        personRepository.save(person);
    }

    private void givenPerson(String name, int age) {
        personRepository.save(new Person(name, age));
    }

}