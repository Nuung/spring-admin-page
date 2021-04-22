package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Item;
import com.example.study.model.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.Optional;

public class UserRepositoryTest extends StudyApplicationTests {

    // CRUD test

    // 자바에서 보통 new 선언자로 object를 직접 만들어서 method에 접근했지만 Autowired -> DI (spring의 강점, 의존성 주입)
    // 을 통해 spring이 이런 의존성과 객체 관리를 통해 우리는 컨트롤할 수 있게 된다!
    @Autowired
    private UserRepository userRepository;

    @Test
    public void create() {

        String account = "Test03";
        String password = "Test03";
        String status = "REGISTERED";
        String email = "Test01@gmail.com";
        String phoneNumber = "010-1111-3333";
        LocalDateTime registeredAt = LocalDateTime.now();
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "AdminServer";



        User user = new User();
        // 위와 다르게 모든 매개변수를 가지는 생성자를 사용하면?
        // 순서, 데이터형 모두 따라줘야함 -> 유지 보수도 존나 귀찮고 힘듦 -> 새로운 생성자가 생기면 ㅈ같음
        // Lombok의 기능! 생성자 패턴! Builder를 사용하자!!
        // User user = new User(account, password, status, email, phoneNumber, registeredAt, )

        user.setAccount(account);
        user.setPassword(password);
        user.setStatus(status);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setRegisteredAt(registeredAt);
        //user.setCreatedAt(createdAt);
        //user.setCreatedBy(createdBy);


        // Lombok builder이용하기! -> 요즘 많이 사용합니다! ~ 롬곡의 어노테이션 이용!!
        User u = User.builder()
                .account(account)
                .password(password)
                .status(status)
                .email(email)
                .build();
        // this를 return하는 method라서 메소드 체이닝이 가능합니다!!


        User newUser = userRepository.save(user);
        Assertions.assertNotNull(newUser);

        /*
        // Insert into .... 와 같은 raw한 query가 쓸 필요가 없어진다! JPA -> ORM의 강점과 이유
        User user = new User(); // 싱글톤
        // user.setId(); // not null / auto increa -> 컨트롤 할 필요 X
        user.setAccount("TestUser03");
        user.setEmail("TestUser03@gmail.com");
        user.setPhoneNumber("010-1111-3333");
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy("TestUser3");

        System.out.println(user);
        User newUser = userRepository.save(user); // return type이 user -> id도 달고나온다!
        System.out.println("newUser : " + newUser);
         */
    }

    @Test
    @Transactional
    public void read(){ // (@RequestParam Long id) {

        User user = userRepository.findFirstByPhoneNumberOrderByIdDesc("010-1111-2222"); // 없으면 null

        // @Accessors(chain = true)의 Lombok 어노테이션으로 아래와 같이 메소드 체이닝 가능함!!
        //user.setEmail("").setPhoneNumber("").setStatus("") // ...

        Assertions.assertNotNull(user);
        System.out.println(user);

        if (user != null) { // optional로 null point 회피 가능합니다~
            // 관계 테스트
            user.getOrderGroup().stream().forEach(orderGroup -> {
                System.out.println("-------------주문 묶음-------------");
                System.out.println(orderGroup);

                System.out.println("-------------주문 상세-------------");
                orderGroup.getOrderDetailList().forEach(orderDetail -> {
                    System.out.println("주문상태: " + orderDetail);

                    // 관계로 더 깊게 파고들어갈 수 있다! -> join 이 쌉가능해지는겨~
                    System.out.println("주문상품: " + orderDetail.getItem().getName()); // detail -> item
                    System.out.println("파트너사 이름: " + orderDetail.getItem().getPartner().getName());
                    System.out.println("고객센터 번호: " + orderDetail.getItem().getPartner().getCallCenter()); // item -> partner
                    // 파트너 통해서 카테고리까지 쌉가능함!
                    System.out.println("파트너사 카테고리: " + orderDetail.getItem().getPartner().getCategory().getTitle());
                });
            });
        }

        /*
        // User user = userRepository.findById(2L); // L은 long -> 이렇게만 하면 빨간줄! Optional 제네릭타입으로 받아야함
        Optional<User> user = userRepository.findById(7L); // 있을수도 있고, 없을 수 도 있다! ~ 카멜케이스

        // 우리가 JPA를 통해 Repository를 만들 것에서 QueryMethod를 만들어서 사용이 가능하다!
        // Optional<User> user = userRepository.findByAccount("TestUser03");
        // Optional<User> user = userRepository.findByEmail("TestUser03@gmail.com");

        // Optional<User> user = userRepository.findById(id); // 있을수도 있고, 없을 수 도 있다!

        // 있을때만 받겠다! 람다식 표현!
        user.ifPresent(selectUser -> {

            // 관계설정이 끝난 뒤! order detail list를 가져와서 확인이 가능하다!!
            selectUser.getOrderDetailList().stream().forEach(detail -> {
                // System.out.println(detail.getId());
                // item 관계 설정까지 했다면? 관계로 인해서 아이템을 가져올 수 있게됨! (관계가 있는)
                Item item = detail.getItem();
                System.out.println(item);
            });



            // System.out.println("selected user: " + selectUser);
            // System.out.println("selected user: " + selectUser.getEmail()); // 요렇게 접근도 가능!
        });

        // userRepository.findAll();
        // return user;
         */
    }

    @Test
    public void update() {
        Optional<User> user = userRepository.findById(2L); // 있을수도 있고, 없을 수 도 있다!
        user.ifPresent(selectUser -> {
            selectUser.setAccount("PPPP");
            selectUser.setUpdatedAt(LocalDateTime.now());
            selectUser.setUpdatedBy("test update method()");
            userRepository.save(selectUser); // create?
            // jap에서는 save를 할때 id기준으로 find를 찾아보고 없으면 create 있으면 update 와 같이 동작한다!!
        });
    }

    @Test
    @Transactional // 기존과 마찬가지로 메소드를 실행하되, 마지막엔 '실제로' 트렌젝션이 일어나 삭제나 업데이트 X -> 롤백을 해준다!
    public void delete() {
        Optional<User> user = userRepository.findById(1L); // 있을수도 있고, 없을 수 도 있다!
        // 무조건 True를 보장해야한다는 의미
        // False가 된다면 org.opentest4j.AssertionFailedError: expected: <true> but was: <false> 와 같이 나옴
        Assertions.assertTrue(user.isPresent()); // 강의 assert 대신 assertions 을 사용 했음,,

        user.ifPresent(selectUser -> {
            userRepository.delete(selectUser); // void임! 리턴 값 없음!
        });

        Optional<User> deleteUser = userRepository.findById(1L); // 삭제 체크
        // 무조건 false라는 의미
        Assertions.assertFalse(deleteUser.isPresent());

        if (deleteUser.isPresent()) System.out.println("데이터 삭제 안됨!: " + deleteUser);
        else System.out.println("데이터 삭제 됨! 데이터 없음!");
    }

    // 실제 api를 만들때는 아래와 같이 사용!!
//    @DeleteMapping("/api/user")
//    public void delete(@RequestParam Long id) {
//
//    }
}
