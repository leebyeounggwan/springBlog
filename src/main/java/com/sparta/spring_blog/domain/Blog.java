package com.sparta.spring_blog.domain;

import com.sparta.spring_blog.dto.BlogRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Blog extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;


    @Column(nullable = false)
    private String content;

    // 기본 패치 타입이 eager
    @ManyToOne
    @JoinColumn(name="USER_ID")
    private Users users;
    // 기본 패치 타입이 lazy
    @OneToMany(mappedBy = "blog")//맵바이=연관관계의 주인이 아니다(fk가 아님)-디비 생성 x
    private List<Comment> comment;

    public Blog(BlogRequestDto requestDto){
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.users = requestDto.getUsers();
    }


    public void update(BlogRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.users = requestDto.getUsers();
    }


}
