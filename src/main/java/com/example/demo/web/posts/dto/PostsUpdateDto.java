package com.example.demo.web.posts.dto;


import com.example.demo.web.posts.entity.Posts;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostsUpdateDto {

    private String title;
    private String content;

    @Builder
    public PostsUpdateDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public PostsUpdateDto(Posts entity) {
        this.title = title;
        this.content = content;
    }
}
