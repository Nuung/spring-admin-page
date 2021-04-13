package com.example.study.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString(exclude = {"user", "item"}) // 서로 상호참조하고 있던 obj들을 자동으로 toString을 호출하게 되면서 무한 루프 -> stack overflow!
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime orderAt;

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

}
