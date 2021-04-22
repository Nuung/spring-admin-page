package com.example.study.model.entity;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString(exclude = {"partner", "orderDetailList"})
@EntityListeners(AuditingEntityListener.class)
@Builder // 생성자패턴!
@Accessors(chain = true) // Test부분 살펴봐
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;

    private String name;

    private String title;

    private String content;

    private Integer price;

    private String brandName;

    private LocalDateTime registeredAt;

    private LocalDateTime unregisteredAt;

    @CreatedDate
    private LocalDateTime createdAt;

    @CreatedBy
    private String createdBy;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @LastModifiedBy
    private String updatedBy;

    // item N : 1 Partner
    @ManyToOne
    private Partner partner;

    // item 1 : N OrderDetail
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
    private List<OrderDetail> orderDetailList;

    /*
    // 1 : N
    // LAZY는 지연 로딩
    // EAGER는 즉시 로딩임 -> ItemRepoTest로 고고
    // LAZY => SELECT * FROM item WHERE id = ?

    // query 길이부터 다름 , 연관관계의 모든 것에 대해!
    // 데이터가 많다면? 한가지 아이템에도 성능저하와 시간 쥬우운내 걸릴듯  -> 그래서 1 : 1 / N : 1 정도일때만 EAGER 쓰자
    // EAGER => SELECT ... JOIN 까지 간다
    // item_id = order_detail.item_id
    // user_id = order_detail.user_id
    // 모두 조인을 마치고 -> WHERE item.id = ?

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
    private List<OrderDetail> orderDetailList;
    */
}
