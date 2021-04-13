package com.example.study.model.entity;

// DB table의 이름과 동일하게, 어퍼 카멜케이스!!

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
//@Table(name = "user"); // 같으면 굳이 할 필요 X
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // mysql을 사용하기 때문에
    private Long id;

//    @Column(name = "account") // 같으면 굳이 할 필요없이 자동으로 매칭해준다
    private String account;

    private String email;

    private String phoneNumber; // jpa에서는 카멜 케이스 -> 스네이크 형식의 다른 변수들도 매칭해준다!

    private LocalDateTime createdAt;

    private String createdBy;

    private LocalDateTime updatedAt;

    private String updatedBy;

    // 1 : N
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user") // orderDetail에 있는 user 변수이름과 동일해야한다!
    private List<OrderDetail> orderDetailList;
}
