package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.User;
import com.example.study.model.enumclass.UserStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.Optional;


public class UserRepositoryTest extends StudyApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void create() {
        User user = new User();
        user.setAccount("TestUser03");
        user.setPassword("1234");
        user.setStatus(UserStatus.REGISTERED);
        user.setPhoneNumber("010-3333-3333");

        User newUser = userRepository.save(user);
        System.out.println(newUser);
    }

    @Test
    @Transactional
    public void read() {
        Optional<User> user = userRepository.findById(1L);

        user.ifPresent(selectUser -> {
            System.out.println("user : " + selectUser);
            selectUser.getOrderGroupList().stream().forEach(orderGroup -> {
                System.out.println("order group : " + orderGroup);
                orderGroup.getOrderDetailList().stream().forEach(orderDetail -> {
                    System.out.println("order detail : " + orderDetail);
                });
            });
        });
    }

    @Test
    public void update() {
        Optional<User> user = userRepository.findById(3L);

        user.ifPresent(selectUser -> {
            selectUser.setEmail("test03@gmail.com");
            userRepository.save(selectUser);
        });
    }

    @Test
    public void delete() {

    }
}