package com.yawn.study.service;

import com.yawn.study.dto.CustomUserDetails;
import com.yawn.study.entity.UserEntity;
import com.yawn.study.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository
    ) {
        this.userRepository = userRepository;
    }

    /**
     * username 은 로그인 페이지에서 username 필드에 입력한 값이 들어오게 됨
     * 들어온 username 은 UserRepo 에서 해당 username 에 해당하는 UserEntity 를 불러오게 됨
     * UserEntity 에서 정보를 CustomUserDetails 에 담아서 Security Config 에 전달
     * @param username
     * @return CustomUserDetails
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity userData = userRepository.findByUsername(username);

        if (userData != null) {
            return new CustomUserDetails(userData);
        }

        return null;
    }
}
