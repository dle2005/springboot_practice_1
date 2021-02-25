package com.example.springboot_practice.repository;

import com.example.springboot_practice.SpringbootPracticeApplicationTests;
import com.example.springboot_practice.model.entity.User;
import com.example.springboot_practice.repasitory.UserRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

public class UserRepositoryTest extends SpringbootPracticeApplicationTests {

    @Autowired // spring에서 지원, 직접 객체를 만들지 않고, spring에서 관리, 의존성을 주입 (Dependency Injection)
    private UserRepository userRepository;

    @Test
    public void create() {
        User user = new User();

        user.setAccount("TestUser03");
        user.setEmail("TestUser03@gmail.com");
        user.setPhoneNumber("010-1111-3333");
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy("TestUser3");

        User newUser = userRepository.save(user);

        System.out.println("newUser : " + newUser);
    }

    // userRepository가 JpaRepository 상속받아 기본적이 crud를 제공
    // read와 관련은 find로 시작하는 keyword
    @Test
    public void read() {
        Optional<User> user = userRepository.findById(2L);

        user.ifPresent(selectUser -> {
            System.out.println("user : " + selectUser);
        });
    }

    @Test
    public void update() {
        Optional<User> user = userRepository.findById(2L);

        user.ifPresent(selectUser -> {
            selectUser.setAccount("PPPP");
            selectUser.setUpdatedAt(LocalDateTime.now());
            selectUser.setUpdatedBy("update method()");

            userRepository.save(selectUser);
        });
    }

    @Test
    @Transactional // 실제 동작은 안일어나게 함, rollback 시킴
    // @DeleteMapping("api/user")
    public void delete() {
        Optional<User> user = userRepository.findById(1L);

        Assert.assertTrue(user.isPresent());

        user.ifPresent(selectUser -> {
            userRepository.delete(selectUser); // delete는 return이 없음
        });

        Optional<User> deleteUser = userRepository.findById(1L);

        Assert.assertFalse(deleteUser.isPresent());
    }
}
