package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Category;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CategoryRepositoryTest extends StudyApplicationTests {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void create() {
        Category category = new Category();
        category.setType("COMPUTER");
        category.setTitle("컴퓨터");
        category.setCreatedAt(LocalDateTime.now());
        category.setCreatedBy("AdminServer");

        Category newCategory = categoryRepository.save(category);
        Assertions.assertNotNull(newCategory);
    }

    @Test
    @Transactional
    public void read() {
        Optional<Category> category = categoryRepository.findById(1L);
        Assertions.assertNotNull(category);

        category.ifPresent(selectCategory -> {
            System.out.println("category : " + category);
            selectCategory.getPartnerList().stream().forEach(partner -> {
                System.out.println("partner : " + partner);
                partner.getItemList().stream().forEach(item -> {
                    System.out.println("item : " + item);
                    item.getOrderDetailList().stream().forEach(orderDetail -> {
                        System.out.println("order detail : " + orderDetail);
                    });
                });
            });
        });
    }
}