package com.example.demo.web.posts.controller;

import com.example.demo.web.posts.service.PostsService;
import com.example.demo.web.posts.dto.PostsResponseDto;
import com.example.demo.web.posts.dto.PostsSaveRequestDto;
import com.example.demo.web.posts.dto.PostsUpdateDto;
import com.example.demo.web.posts.entity.Posts;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto dto) {
        return postsService.save(dto);
    }

    @GetMapping("/api/v1/posts")
    public List<Posts> findAll() {
        return postsService.findAll();
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateDto dto) {
        return postsService.update(id,dto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id) {
        return postsService.findById(id);
    }




}
