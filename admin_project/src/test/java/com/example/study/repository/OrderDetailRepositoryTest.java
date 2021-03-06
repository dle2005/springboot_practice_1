package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Item;
import com.example.study.model.entity.OrderDetail;
import com.example.study.model.entity.OrderGroup;
import com.example.study.model.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class OrderDetailRepositoryTest extends StudyApplicationTests {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderGroupRepository orderGroupRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void create() {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setStatus("WAITING");
        orderDetail.setArrivalDate(LocalDateTime.now().plusDays(2));
        orderDetail.setQuantity(1);
        orderDetail.setTotalPrice(BigDecimal.valueOf(900000));
        orderDetail.setCreatedAt(LocalDateTime.now());
        orderDetail.setCreatedBy("AdminServer");

        Optional<OrderGroup> orderGroup = orderGroupRepository.findById(1L);
        orderGroup.ifPresent(selectOrderGroup -> {
            orderDetail.setOrderGroup(selectOrderGroup);
        });

        Optional<Item> item = itemRepository.findById(1L);
        item.ifPresent(selectItem -> {
            orderDetail.setItem(selectItem);
        });

        OrderDetail newOrderDetail = orderDetailRepository.save(orderDetail);
        Assertions.assertNotNull(newOrderDetail);
        System.out.println(newOrderDetail);
    }

    @Test
    public void read() {
        Optional<OrderDetail> orderDetail = orderDetailRepository.findById(1L);
        Assertions.assertNotNull(orderDetail);

        orderDetail.ifPresent(selectOrderDetail -> {
            System.out.println("order detail : " + selectOrderDetail);
        });
    }
}