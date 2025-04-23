package com.yawn.study.memeber.controller;

import com.yawn.study.memeber.dto.MemberPostDto;
import com.yawn.study.memeber.dto.MemberResponseDto;
import com.yawn.study.memeber.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URI;
import java.util.Objects;

@Slf4j
@Controller
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public ResponseEntity<?> postMember(@RequestBody MemberPostDto requestBody) {
        log.info("MemberController");
        log.info("requestBody Email: " + requestBody.getEmail());
        MemberResponseDto memberResponseDto = memberService.createMember(requestBody);
        URI location = URI.create("/member/" + memberResponseDto.getId());
        return ResponseEntity
                .created(location)
                .body(memberResponseDto);
    }

}
