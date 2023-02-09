package com.example.demo.web.posts;

import com.example.demo.web.posts.entity.Posts;
import com.example.demo.web.posts.repository.PostsRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @AfterEach
    public void clear() {
        postsRepository.deleteAll();
    }

    @Test
    public void saveAndLoad() {
//        given
        String title = "title";
        String content = "content";
        String author = "author";

        Posts post = Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();

//        when
        postsRepository.save(post);

//        then
        Posts posts = postsRepository.findAll().get(0);
        assertThat(posts.getAuthor()).isEqualTo(author);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);

    }



}
