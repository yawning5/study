package com.yawn.study.controller;

import com.yawn.study.dto.JoinDTO;
import com.yawn.study.service.JoinService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class JoinController {

    private final JoinService joinService;

    public JoinController(JoinService joinService) {
        this.joinService = joinService;
    }

    @GetMapping("/join")
    public String joinP() {

        return "join";
    }

    @PostMapping("/joinProc")
    public String joinProcess(JoinDTO joinDTO) {

        log.info("유저 ID: " + joinDTO.getUsername());

        joinService.joinProcess(joinDTO);

        return "redirect:/login";
    }
}
