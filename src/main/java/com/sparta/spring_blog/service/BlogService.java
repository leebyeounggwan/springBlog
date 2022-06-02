package com.sparta.spring_blog.service;

import com.sparta.spring_blog.domain.Blog;
import com.sparta.spring_blog.repository.BlogRepository;
import com.sparta.spring_blog.dto.BlogRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class BlogService {


    private final BlogRepository blogRepository;

    @Transactional
    public Long update(Long id, BlogRequestDto requestDto) {

        Blog blog1 = blogRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다.")
        );
        blog1.update(requestDto);
        return blog1.getId();
    }
}
