package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.AdminUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class AdminUserRepositoryTest extends StudyApplicationTests {

    @Autowired
    private AdminUserRepository adminUserRepository;

    @Test
    public void create() {
        AdminUser adminUser = new AdminUser();
        adminUser.setAccount("AdminUser02");
        adminUser.setPassword("AdminUser02");
        adminUser.setStatus("REGISTERED"); // enum type의 필요성 ~ 오탈자
        adminUser.setRole("PARTNER");

        // JPA config -> AuditorAware -> 어노테이션을 다 달아줬으면 안달아줘도 오토!
        //adminUser.setCreatedAt(LocalDateTime.now());
        //adminUser.setCreatedBy("AdminServer");

        AdminUser newAdminUser = adminUserRepository.save(adminUser);
        Assertions.assertNotNull(newAdminUser);

        // 아래 코드 외에 create만 위에까지 실행하고 -> 아래를 실행해보면 update까지 변동되는 것을 볼 수 있다!
        newAdminUser.setAccount("CHANGE");
        adminUserRepository.save(newAdminUser);

    }
}
