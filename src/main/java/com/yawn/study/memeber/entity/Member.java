package com.yawn.study.memeber.entity;

import com.yawn.study.board.entity.Board;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Member {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;



}
