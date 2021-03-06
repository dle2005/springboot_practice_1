package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Item;
import com.example.study.model.entity.Partner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest extends StudyApplicationTests {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private PartnerRepository partnerRepository;

    @Test
    public void create() {
        Item item = new Item();
        item.setStatus("UNREGISTERED");
        item.setName("삼성 노트북");
        item.setTitle("삼성 노트북 A100");
        item.setContent("2019년형 노트북 입니다");
        item.setPrice(900000);
        item.setBrandName("삼성");
        item.setRegisteredAt(LocalDateTime.now());
        item.setCreatedAt(LocalDateTime.now());
        item.setCreatedBy("Partner01");

        Optional<Partner> partner = partnerRepository.findById(1L);
        partner.ifPresent(selectPartner -> {
            item.setPartner(selectPartner);
        });

        Item newItem = itemRepository.save(item);
        Assertions.assertNotNull(newItem);
//        System.out.println(newItem);
    }

    @Test
    @Transactional
    public void read() {
        Optional<Item> item = itemRepository.findById(1L);
        Assertions.assertNotNull(item);

        item.ifPresent(selectItem -> {
            System.out.println("item : " + selectItem);
            selectItem.getOrderDetailList().stream().forEach(orderDetail -> {
                System.out.println("order detail" + orderDetail);
            });
        });
    }
}