package com.sparta.spring_blog.controller;


import com.sparta.spring_blog.repository.BlogRepository;
import com.sparta.spring_blog.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentRepository commentRepository;


}
