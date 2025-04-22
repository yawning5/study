package com.yawn.study.comment.repository;

import com.yawn.study.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepositroy extends JpaRepository<Comment, Long> {
}
