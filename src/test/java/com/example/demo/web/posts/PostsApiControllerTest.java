package com.example.demo.web.posts;

import com.example.demo.web.posts.dto.PostsResponseDto;
import com.example.demo.web.posts.dto.PostsSaveRequestDto;
import com.example.demo.web.posts.dto.PostsUpdateDto;
import com.example.demo.web.posts.entity.Posts;
import com.example.demo.web.posts.repository.PostsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PostsApiControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    PostsRepository postsRepository;

    @LocalServerPort
    int port;

    @Autowired
    ObjectMapper objectMapper;

    private String title = "title";
    private String content = "content";
    private String author = "author";

    private String title2 = "title2";
    private String content2 = "content2";
    private String author2 = "author2";

    @Test
    void save() throws Exception {

        PostsSaveRequestDto dto = PostsSaveRequestDto.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();


        String url = "http://localhost" + port + "/api/v1/posts";
        mockMvc.perform(post(url)
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().string("1"));

    }


    @Test
    void findAll() throws Exception {
//        given


        Posts save1 = postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build());

        Posts save2 = postsRepository.save(Posts.builder()
                .title(title2)
                .content(content2)
                .author(author2)
                .build());
        List<Posts> posts = new ArrayList<>();
        posts.add(save1);
        posts.add(save2);


//        when + then
        mockMvc.perform(get("http://localhost/api/v1/posts"))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(posts)))
                .andDo(print());
    }

    @Test
    public void update() throws Exception {
//        given

        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build());

        PostsUpdateDto updatedDto = PostsUpdateDto.builder().title(title2).content(content2).build();


//        when
        mockMvc.perform(put("http://localhost/api/v1/posts/1")
                .content(objectMapper.writeValueAsString(updatedDto)).contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().string("1"))
                .andDo(print());
    }

    @Test
    public void findById() throws Exception {
//        given
        Posts save = postsRepository.save(Posts.builder().title(title).content(content).author(author).build());

        PostsResponseDto dto = new PostsResponseDto(save);

//        when + then
        mockMvc.perform(get("http://localhost/api/v1/posts/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(dto)));
    }
}
