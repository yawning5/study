package com.yawn.study;

import com.yawn.study.memeber.entity.Member;
import com.yawn.study.memeber.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@RequiredArgsConstructor
public class StudyApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudyApplication.class, args);
	}

	public final MemberRepository memberRepository;

//	@Bean
//	CommandLineRunner init(MemberRepository memberRepository) {
//		return args -> {
//			Member member = new Member();
//			member.setName("test-user");
//			memberRepository.save(member);
//		};
//	}
}
