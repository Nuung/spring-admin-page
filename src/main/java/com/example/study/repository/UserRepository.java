package com.example.study.repository;

import com.example.study.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository // repo라는 선언 어노테이션
public interface UserRepository extends JpaRepository<User, Long> { // repo의 object type과 long (그 entity의 id의 타입)

    // 한 건으로 가장 최근의 것
    User findFirstByPhoneNumberOrderByIdDesc(String phoneNumber);

    /*
    // 기본적인 CRUD에 대해 코딩없이 바로 사용이 가능하다!!!

    // 쿼리를 메소드 처럼 쓴다고 쿼리메소드임!
    // select * from user where account = ? -> 매개변수가 ?에 해당하는 값
    // userRepo obj로 하단의 사용자설정 쿼리 접근 가능
    Optional<User> findByAccount(String account); // JPA의 QueryMethod!! -> ORM 쿼리를 우리가 원해는데로 커스텀 쌉가능
    Optional<User> findByEmail(String email);

    // 이 쿼리 메소드 매개변수를 ㅈ대로 해도 메소드 이름에서 설정된 컬럼 값 기준으로 가져온다! 하지만 가독성을 위해 잘하자 ^^
    // 위 두개를 섞어서 사용 쌉가능이지~
    Optional<User> findByAccountAndEmail(String account, String email);
     */
}
