package com.sparta.spring_blog.controller;

import com.sparta.spring_blog.domain.Users;
import com.sparta.spring_blog.dto.JoinDto;
import com.sparta.spring_blog.dto.UsersDto;
import com.sparta.spring_blog.repository.UserRepository;
import com.sparta.spring_blog.security.JwtTokenProvider;
import com.sparta.spring_blog.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    private final UsersService usersService;

    // 회원가입
    @PostMapping("/join")
    public String join(@Validated @RequestBody JoinDto joinDto, BindingResult bindingResult) {
//        Optional<Users> found1 = userRepository.findByUsername(joinDto.getUsername());
//        if(found1.isPresent()){ // found가 null이 아니면 true를 출력한다.
//            FieldError fieldError = new FieldError("user", "username", "이미 존재하는 ID입니다.");
//            bindingResult.addError(fieldError);
//        }
//
//        if (joinDto.getPassword().contains(joinDto.getUsername())) {
//            FieldError fieldError = new FieldError("requestDto", "password", "비밀번호에 닉네임과 같은값을 넣을수 없습니다.");
//            bindingResult.addError(fieldError);
//        }
//
//        if(bindingResult.hasErrors()){
//            return "signup";
//        }
            boolean passwordCheckResult = usersService.checkPassword(joinDto);
            boolean overlapCheckResult = usersService.overlapCheck(joinDto);

            if(passwordCheckResult) {
                if(overlapCheckResult){
                    userRepository.save(Users.builder()
                            .username(joinDto.getUsername())
                            .password(passwordEncoder.encode(joinDto.getPassword()))
                            // 최초 가입시 USER 로 설정
                            .roles(Collections.singletonList("ROLE_USER"))
                            .build());
                }
            } else {
                return "비밀번호와 비밀번호 확인이 다릅니다.";

        }


        return "redirect:/login";
    }

    // 로그인
    @PostMapping("/login")
    public UsersDto login(@RequestBody UsersDto user, HttpServletResponse response) {
        Users member = userRepository.findByUsername(user.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 회원 입니다."));
        if (!passwordEncoder.matches(user.getPassword(), member.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
        String token = jwtTokenProvider.createToken(member.getUsername(), member.getRoles());
        response.setHeader("X-AUTH-TOKEN", token);

        Cookie cookie = new Cookie("X-AUTH-TOKEN", token);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        response.addCookie(cookie);

        return new UsersDto();
    }

    @PostMapping("/logout")
    public void logout(HttpServletResponse response){
        Cookie cookie = new Cookie("X-AUTH-TOKEN", null);
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

}
//
//    @PostMapping("/login")
//    public String login(@RequestBody Map<String, String> user) {
//        Users member = userRepository.findByUsername(user.get("username"))
//                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 회원 입니다."));
//        if (!passwordEncoder.matches(user.get("password"), member.getPassword())) {
//            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
//        }
//        return jwtTokenProvider.createToken(member.getUsername(), member.getRoles());
//    }



//    // 회원가입
//    @PostMapping("/join")
//    public String join(@RequestBody Map<String, String> user) {
//
//        userRepository.save(Users.builder()
//                .username(user.get("username"))
//                .password(passwordEncoder.encode(user.get("password")))
//                // 최초 가입시 USER 로 설정
//                .roles(Collections.singletonList("ROLE_USER"))
//                .build());
//        return "index.html";
//    }

//    @PostMapping("/join")
//    public String join(@RequestBody JoinDto user) {
//        boolean result = usersService.checkPassword(user);
//
//        if(result) {
//            userRepository.save(Users.builder()
//                    .username(user.getUsername())
//                    .password(passwordEncoder.encode(user.getPassword()))
//                    // 최초 가입시 USER 로 설정
//                    .roles(Collections.singletonList("ROLE_USER"))
//                    .build());
//        } else {
//            return "error";
//        }
//        return "redirect:/index.html";
//    }

/*
    // 회원가입
    @PostMapping("/join")
    public String join(@Validated @RequestBody JoinDto user, BindingResult bindingResult) {
        boolean passwordCheckResult = usersService.checkPassword(user);
        boolean overlapCheckResult = usersService.overlapCheck(user);

        if(passwordCheckResult) {
            if(overlapCheckResult){
                userRepository.save(Users.builder()
                        .username(user.getUsername())
                        .password(passwordEncoder.encode(user.getPassword()))
                        // 최초 가입시 USER 로 설정
                        .roles(Collections.singletonList("ROLE_USER"))
                        .build());
            }
        } else {
            return "비밀번호와 비밀번호 확인이 다릅니다.";
        }
        return "redirect:/index.html";
    }*/
