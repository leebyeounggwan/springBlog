package com.sparta.spring_blog.service;

import com.sparta.spring_blog.domain.Blog;
import com.sparta.spring_blog.domain.BlogRepository;
import com.sparta.spring_blog.domain.BlogRequestDto;
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

    public Long checkPassword(Long id, BlogRequestDto requestDto) {
        int checkPassword = requestDto.getPassword();
        Blog userPassword = blogRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다.")
        );

        if (userPassword.getPassword() == checkPassword) {

            System.out.println("비밀번호 일치");
            return 0L;
        } else {
            System.out.println("비밀번호 일치하지 않음");
            return 1L;
        }

    }

}
