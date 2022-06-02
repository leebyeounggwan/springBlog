package com.sparta.spring_blog.domain;

import com.sparta.spring_blog.dto.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Comment extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String text;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Users users;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Blog blog;

    public Comment(CommentRequestDto requestDto){
        this.text = requestDto.getText();
        this.users = requestDto.getUsers();
        this.blog = requestDto.getBlog();
    }

}
