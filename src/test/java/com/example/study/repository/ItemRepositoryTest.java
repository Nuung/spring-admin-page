package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

public class ItemRepositoryTest extends StudyApplicationTests {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void create() {

        Item item = new Item();
        item.setStatus("UNREGISTERED");
        item.setName("삼성 노트북");
        item.setTitle("삼성 노트북 A100");
        item.setContent("2020년형 같지만 2019년형인 소비자 기만 전용 노트북입니다");
        item.setPrice(1200000);
        item.setBrandName("삼성");
        item.setRegisteredAt(LocalDateTime.now());
        item.setCreatedAt(LocalDateTime.now());
        item.setCreatedBy("Partner01");
        item.setPartnerId(1L);

        Item newItem = itemRepository.save(item);
        Assertions.assertNotNull(newItem);
        Assertions.assertEquals(newItem.getName(), "삼성 노트북");

        /*북
        Item item = new Item();
        item.setName("LabTop");
        item.setPrice(1000000);
        item.setContent("Samsoung");

        Item newItem = itemRepository.save(item);
        Assertions.assertNotNull(newItem);
         */
    }

    @Test
    public void read() {
        Long id = 1L;

        Optional<Item> item = itemRepository.findById(id);

        Assertions.assertTrue(item.isPresent());

        // 있으면, i (람다식) 표현
        item.ifPresent(i -> {
            System.out.println(i);
        });
    }
}
