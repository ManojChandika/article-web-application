package com.example.articlewebapplication.service;

import com.example.articlewebapplication.dto.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(Long postId, CommentDto commentDto);
    List<CommentDto> getCommentsByPostId(Long postId);
    CommentDto getCommentById(Long postId,Long commentId);
    CommentDto updateComment(Long postId,Long commentId,CommentDto requestCommentDto);
    void deleteComment(Long postId, Long commentId);
}
