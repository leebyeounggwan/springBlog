package com.sparta.spring_blog.domain;

import com.sparta.spring_blog.domain.BlogRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Blog extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private int password;

    @Column(nullable = false)
    private String content;



    public Blog(BlogRequestDto requestDto){
        this.title = requestDto.getTitle();
        this.userName = requestDto.getUserName();
        this.password = requestDto.getPassword();
        this.content = requestDto.getContent();
    }


    public void update(BlogRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.userName = requestDto.getUserName();
        this.password = requestDto.getPassword();
        this.content = requestDto.getContent();
    }


}
