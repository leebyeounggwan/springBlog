package com.sparta.spring_blog;

import com.sparta.spring_blog.domain.Blog;
import com.sparta.spring_blog.domain.BlogRepository;
import com.sparta.spring_blog.domain.BlogRequestDto;
import com.sparta.spring_blog.service.BlogService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.List;


@EnableJpaAuditing
@SpringBootApplication
public class SpringBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBlogApplication.class, args);
    }


}


//    @Bean
//    public CommandLineRunner demo(BlogRepository blogRepository, BlogService blogService) {
//        return (args) -> {
//            blogRepository.save(new Blog("게시글1", "이병관", 1234, "테스트"));
//
//            System.out.println("데이터 인쇄");
//            List<Blog> blogList = blogRepository.findAll();
//            for (int i=0; i<blogList.size(); i++) {
//                Blog blog = blogList.get(i);
//                System.out.println(blog.getId());
//                System.out.println(blog.getTitle());
//                System.out.println(blog.getUserName());
//                System.out.println(blog.getPassword());
//                System.out.println(blog.getContent());
//
//            }
//
//            BlogRequestDto requestDto = new BlogRequestDto("게시글1", "이이이", 1234, "업데이트됨");
//            blogService.update(1L, requestDto);
//            blogList = blogRepository.findAll();
//            for (int i=0; i<blogList.size(); i++) {
//                Blog blog = blogList.get(i);
//                System.out.println(blog.getId());
//                System.out.println(blog.getTitle());
//                System.out.println(blog.getUserName());
//                System.out.println(blog.getPassword());
//                System.out.println(blog.getContent());
//            }
//        };
//    }