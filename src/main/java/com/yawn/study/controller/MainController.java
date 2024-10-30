package com.yawn.study.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;
import java.util.Iterator;

@Controller
public class MainController {

    /**
     * Model 은 서버사이드 렌더링 방식으로 서버를 구현할때
     * 서버에서 뷰로 데이터를 전달하는 상황에 사용한다
     * @param model
     * @return
     */
    @GetMapping("/")
    public String mainP(Model model) {

        /*
        사용자가 인증 한 후에는 인증 된 UserDetails 가 Authentication 객체로
        SecurityContextHolder 에 저장되게 된다. 이렇게 저장된 객체는 스레드내에서 전역적 접근가능
         */
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String id = authentication.getName();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();
        String role = auth.getAuthority();

        model.addAttribute("id", id);
        model.addAttribute("role", role);

        return "main";
    }
}
