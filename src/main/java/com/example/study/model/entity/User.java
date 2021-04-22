package com.example.study.model.entity;

// DB table의 이름과 동일하게, 어퍼 카멜케이스!!

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
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString(exclude = {"orderGroup"}) // stackOverFlow (서로 tostring 하면서 무한 참조 방지)
//@Table(name = "user"); // 같으면 굳이 할 필요 X
@EntityListeners(AuditingEntityListener.class)
@Builder // 생성자패턴!
@Accessors(chain = true) // Test부분 살펴봐
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // mysql을 사용하기 때문에
    private Long id;

//    @Column(name = "account") // 같으면 굳이 할 필요없이 자동으로 매칭해준다
    private String account;

    private String password;

    private String status;

    private String email;

    private String phoneNumber; // jpa에서는 카멜 케이스 -> 스네이크 형식의 다른 변수들도 매칭해준다!

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

    // User 1 : N OrderGroup
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<OrderGroup> orderGroup;

    // 1 : N
    /*
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user") // orderDetail에 있는 user 변수이름과 동일해야한다!
    private List<OrderDetail> orderDetailList;
     */
}
