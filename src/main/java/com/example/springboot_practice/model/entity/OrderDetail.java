package com.example.springboot_practice.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString(exclude = {"user", "item"}) // user와 userList가 toString을 상호 참조하여 오버플로우 발생
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime orderAt;

    // orderDetail 입장에선 N : 1
    @ManyToOne
    private User user;
    // private Long userId;

    @ManyToOne
    private Item item;
}
