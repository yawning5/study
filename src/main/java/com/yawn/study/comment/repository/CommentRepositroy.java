package com.yawn.study.comment.repository;

import com.yawn.study.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepositroy extends JpaRepository<Comment, Long> {
    @Query("SELECT c " +
            "FROM Comment c " +
            "WHERE c.member.id = :memberId")
    List<Comment> findAllCommentsByMemberID(@Param("memberId") Long memberId);
}
