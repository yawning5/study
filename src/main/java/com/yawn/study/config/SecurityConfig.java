package com.yawn.study.config;

import com.yawn.study.service.CustomUserDetailsService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

@Configuration

/*
보안 설정 클래스로 지정: Spring Security 의 보안 설정을 담당하는 클래스 임을 나타내고
Spring 이 해당 클래스에서 제공하는 보안 설정을 적용함

기본 보안 설정 대체: Spring Boot 는 기본적으로 자동 구성을 통해 기본 보안 설정을 제공,
하지만 @EnableWebSecurity 를 사용하면 이러한 기본 설정을 비활성화하고, 개발자가 저으이한 설정을 우선시함
 */
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;

    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    /*
    Bean 으로 등록하면 Spring 컨테이너에 등록되어
    의존성 주입을 통해 어디서든 사용 가능하게 함
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // 아래에 커스텀 인증매니저 만든 것을 사용한다고 등록
        http
                .authenticationManager(authenticationManager());

        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/login", "/loginProc", "/join", "/joinProc").permitAll()
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .requestMatchers("/my/**").hasAnyRole("ADMIN", "USER")
                        .anyRequest().authenticated()
                );

        /*
        Post 작업을 할때 csrf 토큰을 보내줘야 필터를 통과함
         */
//        httpSecurity
//                .csrf(auth -> auth.disable());

        http
                /*
                권한이 없는 유저가 권한이 필요한 페이지로 들어갈때 띄워주는 로그인 페이지 설정
                 */
                .formLogin(auth -> auth
                        .loginPage("/login")
                        /*
                        프론트에서 로그인 데이터를 넘기면 시큐리티가 받아서 로그인 처리를 진행
                         */
                        .loginProcessingUrl("/loginProc")
                        .permitAll()
                );

        http
                .sessionManagement(auth -> auth
                        .maximumSessions(1)
                        .maxSessionsPreventsLogin(true));

        http
                .sessionManagement(auth -> auth
                        .sessionFixation().changeSessionId());



        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager() {

        // ✅ 1. DB 인증 제공자
        DaoAuthenticationProvider dbProvider = new DaoAuthenticationProvider();
        dbProvider.setUserDetailsService(customUserDetailsService);
        dbProvider.setPasswordEncoder(bCryptPasswordEncoder());

        // ✅ 2. 인메모리 admin 유저
        UserDetails admin = User.builder()
                .username("admin")
                .password(bCryptPasswordEncoder().encode("1234"))
                .roles("ADMIN")
                .build();

        // 인메모리 유저 만든것을 등록
        InMemoryUserDetailsManager memoryUserDetailsManager = new InMemoryUserDetailsManager(admin);

        DaoAuthenticationProvider memoryProvider = new DaoAuthenticationProvider();
        memoryProvider.setUserDetailsService(memoryUserDetailsManager);
        memoryProvider.setPasswordEncoder(bCryptPasswordEncoder());

        return new ProviderManager(List.of(
                memoryProvider,  // 먼저 시도
                dbProvider       // 실패하면 DB
        ));
    }
}
