package com.example.demo.web.posts.service;

import com.example.demo.web.posts.repository.PostsRepository;
import com.example.demo.web.posts.dto.PostsResponseDto;
import com.example.demo.web.posts.dto.PostsSaveRequestDto;
import com.example.demo.web.posts.dto.PostsUpdateDto;
import com.example.demo.web.posts.entity.Posts;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class PostsService {


    private final PostsRepository postsRepository;
    
    public Long save(PostsSaveRequestDto dto) {
        return postsRepository.save(dto.toEntity()).getId();
    }
    public List<Posts> findAll() {
        return postsRepository.findAll();
    }

    @Transactional
    public Long update(Long id, PostsUpdateDto dto) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Not found"));
        entity.update(dto.getTitle(),dto.getContent());
        return id;
    }
    public PostsResponseDto findById(Long id) {
        Posts found = postsRepository.findById(id)
                .orElseThrow((() -> new IllegalArgumentException("Not found")));
        return new PostsResponseDto(found);
    }
}
