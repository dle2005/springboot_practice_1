package com.information.management.demo.repository;

import com.information.management.demo.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    List<Person> findByName(String name);

    List<Person> findByBlockIsNull();

    List<Person> findByBirthdayBetween(LocalDate startDate, LocalDate endDate);

}
