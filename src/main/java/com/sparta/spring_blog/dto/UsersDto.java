package com.sparta.spring_blog.dto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@RequiredArgsConstructor
public class UsersDto {


    private String username;
    private String password;
}
