package com.yawn.study.repository;

import com.yawn.study.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/*
JpaRepository<T, ID>
T: 레포지토리가 관리할 엔티티의 타입
ID: 엔티티의 식별자(Primary Key)의 타입
 */
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    boolean existsByUsername(String username);

    UserEntity findByUsername(String username);
}
