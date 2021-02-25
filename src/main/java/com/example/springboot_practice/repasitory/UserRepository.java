package com.example.springboot_practice.repasitory;

import com.example.springboot_practice.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Repository
// 따로 쿼리문을 작성하지 않아도 기본적인 interface를 통해서
// create, read, update, delete 사용할 수 있음
// annotation Repository랑 JpaRepository를 상속 받아 사용

@Repository
public interface UserRepository extends JpaRepository<User, Long> { // <table명, primary key>

}
