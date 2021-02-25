package com.example.springboot_practice.model.entity;

// Entity : JAP에서는 테이블을 자동으로 생성해주는 기능 존재
// DB Table == JPA Entity

// Annotation           용도
// @Entity              해당 Class가 Entity임을 명시
// @Table               실제 DB테이블의 이름을 명시
// @Id                  Index primary key를 명시
// @Column              실제 DB Column의 이름을 명시
// @GeneratedValue      Primary key 식별키의 전략 설정

// carmelCase와 snake_case는 자동으로 매칭 시켜줌

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Entity
// @Table(name = "user") // table과 class의 이름이 동일하면 선언하지 않아도 됨
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String account;

    private String email;

    private String phoneNumber;

    private LocalDateTime createdAt;

    private String createdBy;

    private LocalDateTime updatedAt;

    private String updatedBy;

}
