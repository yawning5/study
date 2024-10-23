package com.yawn.study.service;

import com.yawn.study.dto.JoinDTO;
import com.yawn.study.entity.UserEntity;
import com.yawn.study.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JoinService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public JoinService(UserRepository userRepository,
                       BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void joinProcess(JoinDTO joinDTO) {

        // db 에 이미 동일한 username 을 가진 회원이 존재하는지?
        boolean isUser = userRepository.existsByUsername(joinDTO.getUsername());
        if (isUser) {
            return;
        }

        UserEntity data = new UserEntity();

        data.setUsername(joinDTO.getUsername());
        data.setPassword(passwordEncoder.encode(joinDTO.getPassword()));
        data.setRole("ROLE_ADMIN");

        userRepository.save(data);
    }
}
