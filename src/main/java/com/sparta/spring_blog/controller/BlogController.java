package com.sparta.spring_blog.controller;

import com.sparta.spring_blog.domain.Blog;
import com.sparta.spring_blog.domain.Users;
import com.sparta.spring_blog.repository.BlogRepository;
import com.sparta.spring_blog.dto.BlogRequestDto;
import com.sparta.spring_blog.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class BlogController {

    private final BlogRepository blogRepository;

    private final BlogService blogService;


    @PostMapping("/api/blogs")
    public String createBlog(@RequestBody BlogRequestDto requestDto, @AuthenticationPrincipal Users users) {

        Blog blog = new Blog(requestDto);
        blog.setUsers(requestDto.getUsers());
        if(users==null){
            return "error";
        }
        blog.setUsers(users);
        blogRepository.save(blog);
        return "/";
    }

    @GetMapping("/api/blogs")
    public List<Blog> getBlogs() {
        return blogRepository.findAllByOrderByModifiedAtDesc();
    }

    @GetMapping("/api/blogs/{id}")
    public Blog getBlogOne(@PathVariable Long id) {
        return blogRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다.")
        );
    }


    @PutMapping("/api/blogs/{id}")
    public Long updateBlog(@PathVariable Long id, @RequestBody BlogRequestDto requestDto) {

        return blogService.update(id, requestDto);
    }

    @DeleteMapping("/api/blogs/{id}")
    public Long deleteBlog(@PathVariable Long id, @RequestBody BlogRequestDto requestDto) {

        blogRepository.deleteById(id);
        return id;
    }
}



/*
*
*  @PostMapping("/api/blogs")
    public String createBlog( @RequestBody BlogRequestDto requestDto) {

        Blog blog = new Blog(requestDto);
        blog.setUsers(requestDto.getUsers());
        if(blog.getUsers()==null){
            return "error";
        }
        blogRepository.save(blog);
        return "redirect:/";
    }
    *
    *
*
*  */