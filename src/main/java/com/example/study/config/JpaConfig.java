package com.example.study.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration // 이건 설정이다!
@EnableJpaAuditing // JPA에 대해 감시를 활성화 시키겠다
public class JpaConfig {
}
