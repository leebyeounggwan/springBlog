package com.sparta.spring_blog.dto;

import com.sparta.spring_blog.domain.Blog;
import com.sparta.spring_blog.domain.Users;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class CommentRequestDto {
    private String text;
    private Blog blog;
    private Users users;
}
