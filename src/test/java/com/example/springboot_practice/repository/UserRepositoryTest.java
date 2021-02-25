package com.example.springboot_practice.repository;

import com.example.springboot_practice.SpringbootPracticeApplicationTests;
import com.example.springboot_practice.model.entity.User;
import com.example.springboot_practice.repasitory.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

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

    public void read() {

    }

    public void update() {

    }

    public void delete() {

    }
}
