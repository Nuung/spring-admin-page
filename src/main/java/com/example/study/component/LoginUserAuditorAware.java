package com.example.study.component;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LoginUserAuditorAware implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() { // 현재의 감시자는 누구로 줄꺼냐 -> create by / update by가 일어날때 어떤 이름을 줄꺼냐
        return Optional.of("AdminServer");
        // return Optional.empty();
    }
}
