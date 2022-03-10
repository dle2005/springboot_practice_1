package spring.batch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.batch.entity.domain.User;

import java.time.LocalDate;
import java.util.Collection;

public interface UserRepository extends JpaRepository<User, Long> {
    Collection<Object> findAllByUpdatedDate(LocalDate now);
}
