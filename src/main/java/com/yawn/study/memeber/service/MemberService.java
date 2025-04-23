package com.yawn.study.memeber.service;

import com.yawn.study.memeber.dto.MemberPostDto;
import com.yawn.study.memeber.dto.MemberResponseDto;
import com.yawn.study.memeber.entity.Member;
import com.yawn.study.memeber.exception.EmailAlreadyExistsException;
import com.yawn.study.memeber.mapper.MemberMapper;
import com.yawn.study.memeber.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class MemberService {

    private final MemberMapper memberMapper;
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public MemberService(MemberRepository memberRepository,
                         MemberMapper memberMapper,
                         BCryptPasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.memberMapper = memberMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public MemberResponseDto createMember(MemberPostDto memberPostDto) {
        log.info("MemberService.createMember");
        log.info("DTO Email: " + memberPostDto.getEmail());
        memberPostDto.setEncodePassword(passwordEncoder.encode(memberPostDto.getPassword()));
        Member member = memberMapper.toMember(memberPostDto);
        log.info("mapper 구동 확인: " + member.getEmail());
        verifyExistEmail(member);
        memberRepository.save(member);
        MemberResponseDto memberResponseDto = new MemberResponseDto();
        memberResponseDto.setId(member.getId());
        memberResponseDto.setNickname(member.getNickname());
        return memberResponseDto;
    }

    public void verifyExistEmail(Member member) {
        Optional<Member> findMember = memberRepository.findByEmail(member.getEmail());
        if (findMember.isPresent()) throw new EmailAlreadyExistsException("이미 사용중인 이메일 입니다: " + member.getEmail());
    }
}
