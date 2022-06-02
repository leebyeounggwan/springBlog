package com.sparta.spring_blog.service;

import com.sparta.spring_blog.domain.Blog;
import com.sparta.spring_blog.domain.Users;
import com.sparta.spring_blog.dto.BlogRequestDto;
import com.sparta.spring_blog.dto.JoinDto;
import com.sparta.spring_blog.dto.UsersDto;
import com.sparta.spring_blog.repository.BlogRepository;
import com.sparta.spring_blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.bridge.Message;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UsersService {
    private final UserRepository userRepository;

    public boolean checkPassword(JoinDto requestDto) {
        if(requestDto.getPassword().equals(requestDto.getAgainPassword())){
            return true;
        }
        return false;
    }

    public boolean overlapCheck(JoinDto requestDto) {
        Optional<Users> member = userRepository.findByUsername(requestDto.getUsername());
        if(member.isPresent()){
            return false;
        }else{
            return true;
        }
    }
}
