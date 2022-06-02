package com.sparta.spring_blog.security;

import com.sparta.spring_blog.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
/*
* 토큰에 저장된 유저 정보를 활용해야 하기 때문에
* CustomUserDetatilService 라는 이름의 클래스를 만들고
*  UserDetailsService를 상속받아 재정의 하는 과정
* */

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
    }
}


    /*loadUserByUsername()에서 하는 일
    username을 가지고 사용자 정보를 조회
        사용자의 Role과 권한(Privilege)을 조회하여, SimpleGrantedAuthority 목록을 authorities에 세팅한다.
        Authentication 내부 principal 객체에 UserDetails 객체가 저장된다.
        Authentication 내부 authorities 객체에 사용자의 Role과 권한(Privilege) 정보가 저장된다.
        UserDetails에 authorities가 세팅되어 있어야, API별 role이나 권한 체크를 진행할 수 있다.*/

    /*@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return (UserDetails) userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
    }*/