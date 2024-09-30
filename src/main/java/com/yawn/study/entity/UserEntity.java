package com.yawn.study.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /*
    아이디 중복방지
     */
    @Column(unique = true)
    private String username;
    private String password;

    private String role;
}
