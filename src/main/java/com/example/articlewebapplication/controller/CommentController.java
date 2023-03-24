package com.example.articlewebapplication.controller;

import com.example.articlewebapplication.dto.CommentDto;
import com.example.articlewebapplication.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/posts")
public class CommentController {
    @Autowired
    private CommentService commentService;

    // Create Comment
    @PostMapping("{postId}/comments")
    public ResponseEntity<CommentDto> createComment(
            @PathVariable(value = "postId") long postId,
            @Valid @RequestBody CommentDto commentDto
    ){
        return new ResponseEntity<>(commentService.createComment(postId,commentDto), HttpStatus.CREATED);
    }

    // Get All Comments by Post id
    @GetMapping("{postId}/comments")
    public ResponseEntity<List<CommentDto>> getCommentsByPostId(
            @PathVariable(value = "postId") Long postId
    ){
        return ResponseEntity.ok(commentService.getCommentsByPostId(postId));
    }

    // Get Comment by id
    @GetMapping("{postId}/comments/{id}")
    public ResponseEntity<CommentDto> getCommentById(
            @PathVariable(value = "postId") Long postId,
            @PathVariable(value = "id") Long commentId
    ){
        return ResponseEntity.ok(commentService.getCommentById(postId,commentId));
    }

    //Update Comment
    @PutMapping("{postId}/comments/{id}")
    public ResponseEntity<CommentDto> updateComment(
            @PathVariable(value = "postId") Long postId,
            @PathVariable(value = "id") Long commentId,
            @Valid @RequestBody CommentDto requestCommentDto
    ){
        return ResponseEntity.ok(commentService.updateComment(postId,commentId,requestCommentDto));
    }

    // Delete comment
    @DeleteMapping("{postId}/comments/{id}")
    public ResponseEntity<String> deleteComment(
            @PathVariable(value = "postId") Long postId,
            @PathVariable(value = "id") Long commentId
    ){
        commentService.deleteComment(postId,commentId);
        return ResponseEntity.ok("Comment Delete Successfully");
    }
}
