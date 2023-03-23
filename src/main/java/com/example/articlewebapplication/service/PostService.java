package com.example.articlewebapplication.service;

import com.example.articlewebapplication.dto.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);
    List<PostDto> getAllPosts();
    PostDto getPostById(Long id);
    PostDto updatePost(PostDto postDTO, Long id);
    void deletePost(Long id);
}
