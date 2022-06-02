package com.sparta.spring_blog.dto;


import com.sparta.spring_blog.domain.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@RequiredArgsConstructor
public class BlogRequestDto {
    private final String title;
    private final Users users;
    private final String content;
}
