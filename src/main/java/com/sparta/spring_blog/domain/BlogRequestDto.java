package com.sparta.spring_blog.domain;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@RequiredArgsConstructor
public class BlogRequestDto {
    private final String title;
    private final String userName;
    private final int password;
    private final String content;
}
