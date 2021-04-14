package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.OrderDetail;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderDetailRepositoryTest extends StudyApplicationTests {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    public void create() {

        OrderDetail orderDetail = new OrderDetail();

        orderDetail.setStatus("WAITING");
        orderDetail.setArrivalDate(LocalDateTime.now().plusDays(2));
        orderDetail.setQuantity(1);
        orderDetail.setTotalPrice(BigDecimal.valueOf(1200000)); // big decimal casting 필요
        // orderDetail.setOrderAt(LocalDateTime.now()); -> 예전의 잔재 ㅎ
        orderDetail.setOrderGroupId(1L); // 어떠한 장바구니에! order group에!!
        orderDetail.setItemId(1L); // 어떤 상품을?
        orderDetail.setCreatedAt(LocalDateTime.now());
        orderDetail.setCreatedBy("AdminServer");

        OrderDetail newOrderDetail = orderDetailRepository.save(orderDetail);
        Assertions.assertNotNull(newOrderDetail);

        /*
        OrderDetail orderDetail = new OrderDetail();

        orderDetail.setOrderAt(LocalDateTime.now());
        // orderDetail.setUserId(7L); // 어떤 사람이?
        // orderDetail.setItemId(1L); // 어떤 상품을?

        OrderDetail newOrderDetai = orderDetailRepository.save(orderDetail);
        Assertions.assertNotNull(newOrderDetai);
         */
    }

}
