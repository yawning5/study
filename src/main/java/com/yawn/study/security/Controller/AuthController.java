package com.yawn.study.security.Controller;

import com.yawn.study.security.dto.CustomUserDetails;
import com.yawn.study.security.dto.LoginDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/login")
@Slf4j
public class AuthController {

    private final AuthenticationManager authenticationManager;

    public AuthController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto
            , HttpServletRequest httpRequest) {

        log.info("로그인 시도: {}", loginDto.getId());

        // 표시된 부분
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(loginDto.getId(), loginDto.getPassword());

        Authentication authentication = authenticationManager.authenticate(token);
        // 은 서비스단에서 처리하는게 관심사 분리에 맞음


        // 아래는 세션처리 내용이기 때문에 컨트롤러에서
        log.info("로그인 성공 - 사용자: {}, 인증 여부: {}",
                authentication.getName(),
                authentication.isAuthenticated());

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);

        httpRequest.getSession(true);
        httpRequest.getSession().setAttribute(
                HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                context
        );

//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        httpRequest.getSession(true);

//        return ResponseEntity.ok("로그인 성공");

        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create("/login/test"))
                .build();

    }

    @GetMapping("/test")
    public ResponseEntity<?> test(HttpServletRequest request, @AuthenticationPrincipal CustomUserDetails user) {
        log.info("현재 로그인한 사용자: {}", user);
        return ResponseEntity.ok(user.getUsername());
    }
}
