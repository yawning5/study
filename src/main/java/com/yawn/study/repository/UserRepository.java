package com.yawn.study.repository;

import com.yawn.study.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/*
JpaRepository<T, ID>
T: 레포지토리가 관리할 엔티티의 타입
ID: 엔티티의 식별자(Primary Key)의 타입
 */
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    Optional<UserEntity> findByUsername(String username); // ✅ Optional로 변경

    boolean existsByUsername(String username);

//    UserEntity findByUsername(String username);
}
