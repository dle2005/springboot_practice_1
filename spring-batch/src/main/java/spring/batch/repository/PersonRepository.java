package spring.batch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.batch.entity.domain.Person;


public interface PersonRepository extends JpaRepository<Person, Integer> {
}
