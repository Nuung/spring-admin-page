package com.example.study.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer price;

    private String content;

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
}
