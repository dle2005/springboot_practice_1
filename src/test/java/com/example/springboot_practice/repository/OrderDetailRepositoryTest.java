package com.example.springboot_practice.repository;

import com.example.springboot_practice.SpringbootPracticeApplicationTests;
import com.example.springboot_practice.model.entity.OrderDetail;
import com.example.springboot_practice.repasitory.OrderDetailRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class OrderDetailRepositoryTest extends SpringbootPracticeApplicationTests {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    public void create() {
        OrderDetail orderDetail = new OrderDetail();

        orderDetail.setOrderAt(LocalDateTime.now());

        // orderDetail.setUserId(7L);

        // orderDetail.setItemId(1L);

        OrderDetail newOrderDetail = orderDetailRepository.save(orderDetail);
        Assert.assertNotNull(newOrderDetail);
    }
}
