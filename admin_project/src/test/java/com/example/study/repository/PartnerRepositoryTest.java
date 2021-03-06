package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Category;
import com.example.study.model.entity.Partner;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PartnerRepositoryTest extends StudyApplicationTests {

    @Autowired
    private PartnerRepository partnerRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void create() {
        Partner partner = new Partner();
        partner.setName("Partner01");
        partner.setStatus("REGISTERED");
        partner.setAddress("서울시 강남구");
        partner.setCallCenter("070-1111-2222");
        partner.setPartnerNumber("010-1111-2222");
        partner.setCeoName("홍길동");
        partner.setRegisteredAt(LocalDateTime.now());
        partner.setCreatedAt(LocalDateTime.now());
        partner.setCreatedBy("AdminServer");

        Optional<Category> category = categoryRepository.findById(1L);
        category.ifPresent(selectCategory -> {
            partner.setCategory(selectCategory);
        });

        Partner newPartner = partnerRepository.save(partner);
        System.out.println(newPartner);
    }

    @Test
    @Transactional
    public void read() {
        Optional<Partner> partner = partnerRepository.findById(1L);

        partner.ifPresent(selectPartner -> {
            System.out.println("partner : " + selectPartner);
            selectPartner.getItemList().stream().forEach(item -> {
                System.out.println("item : " + item);
                item.getOrderDetailList().stream().forEach(orderDetail -> {
                    System.out.println("order detail" + orderDetail);
                });
            });
        });

    }
}