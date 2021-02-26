package com.example.springboot_practice.repasitory;

import com.example.springboot_practice.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// Repository
// 따로 쿼리문을 작성하지 않아도 기본적인 interface를 통해서
// create, read, update, delete 사용할 수 있음
// annotation Repository랑 JpaRepository를 상속 받아 사용

@Repository
public interface UserRepository extends JpaRepository<User, Long> { // <table명, primary key>

    // JPA가 findBy까지 보고 select인 것을 인지
    // findBy 뒤의 대문자를 보고 매칭
    // select * from user where account = ? << test03, test04
    Optional<User> findByAccount(String account);

    Optional<User> findByEmail(String email);

    // select * from user where account = ? and email = ?
    Optional<User> findByAccountAndEmail(String account, String email);
}
