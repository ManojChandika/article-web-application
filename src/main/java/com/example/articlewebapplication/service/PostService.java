package com.example.articlewebapplication.service;

import com.example.articlewebapplication.dto.PostDto;
import com.example.articlewebapplication.dto.PostResponse;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);
    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);
    PostDto getPostById(Long id);
    PostDto updatePost(PostDto postDTO, Long id);
    void deletePost(Long id);
    List<PostDto> getPostsByCategoryId(Long categoryId);
}
