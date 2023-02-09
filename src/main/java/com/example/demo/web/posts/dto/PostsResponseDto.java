package com.example.demo.web.posts.dto;

import com.example.demo.web.posts.entity.Posts;
import lombok.Getter;

@Getter
public class PostsResponseDto {

    private String title;
    private String content;

    public PostsResponseDto(Posts posts) {
        this.title = posts.getTitle();
        this.content = posts.getContent();
    }
}
