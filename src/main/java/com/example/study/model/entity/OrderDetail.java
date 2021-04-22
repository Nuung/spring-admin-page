package com.example.study.model.entity;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString(exclude = {"item", "orderGroup"})
// @ToString(exclude = {"user", "item"}) // 서로 상호참조하고 있던 obj들을 자동으로 toString을 호출하게 되면서 무한 루프 -> stack overflow!
@EntityListeners(AuditingEntityListener.class)
@Builder // 생성자패턴!
@Accessors(chain = true) // Test부분 살펴봐
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;

    // private LocalDateTime orderAt;

    private LocalDateTime arrivalDate;

    private Integer quantity; // 수량

    private BigDecimal totalPrice;

    @CreatedDate
    private LocalDateTime createdAt;

    @CreatedBy
    private String createdBy;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @LastModifiedBy
    private String updatedBy;

    // OrderDetail N : 1 item
    @ManyToOne
    private Item item;

    // OrderDetail N : 1 OrderGroup
    @ManyToOne
    private OrderGroup orderGroup;

    /*
    // private Long userId; 에서 아래로 변경
    // 관계 설정하기!!
    // N : 1
    @ManyToOne
    private User user;

    // private Long itemId;
    // 관계 설정하기!!
    // N : 1
    @ManyToOne
    private Item item;
     */
}
