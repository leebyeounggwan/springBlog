package com.sparta.spring_blog.controller;

import com.sparta.spring_blog.domain.Blog;
import com.sparta.spring_blog.domain.BlogRepository;
import com.sparta.spring_blog.domain.BlogRequestDto;
import com.sparta.spring_blog.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BlogController {

    private final BlogRepository blogRepository;

    private final BlogService blogService;


    @PostMapping("/api/blogs")
    public Blog createBlog(@RequestBody BlogRequestDto requestDto) {

        Blog blog = new Blog(requestDto);


        return blogRepository.save(blog);
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
        if (blogService.checkPassword(id, requestDto)==0L){
            return blogService.update(id, requestDto);
        } else {
            return id;
        }

    }

    @DeleteMapping("/api/blogs/{id}")
    public Long deleteBlog(@PathVariable Long id, @RequestBody BlogRequestDto requestDto) {
        if (blogService.checkPassword(id, requestDto)==0L){
            blogRepository.deleteById(id);
        } else {
            return id;
        }
        return id;
    }
}

