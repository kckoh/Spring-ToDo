package com.example.demo.web.posts.dto;

import lombok.Builder;

public class PostsGetReqeustDto {
    private String title;
    private String content;
    private String author;

    @Builder
    public PostsGetReqeustDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
