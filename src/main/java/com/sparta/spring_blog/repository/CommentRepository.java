package com.sparta.spring_blog.repository;

import com.sparta.spring_blog.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByBlogIdOrderByModifiedAt(Long id);
}
