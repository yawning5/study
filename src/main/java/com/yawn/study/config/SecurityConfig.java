package com.yawn.study.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration

/*
보안 설정 클래스로 지정: Spring Security 의 보안 설정을 담당하는 클래스 임을 나타내고
Spring 이 해당 클래스에서 제공하는 보안 설정을 적용함

기본 보안 설정 대체: Spring Boot 는 기본적으로 자동 구성을 통해 기본 보안 설정을 제공,
하지만 @EnableWebSecurity 를 사용하면 이러한 기본 설정을 비활성화하고, 개발자가 저으이한 설정을 우선시함
 */
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/login").permitAll()
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .requestMatchers("/my/**").hasAnyRole("ADMIN", "USER")
                        .anyRequest().authenticated()
                );



        return httpSecurity.build();
    }
}
