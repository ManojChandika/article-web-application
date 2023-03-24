package com.example.articlewebapplication.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class PostDto {
        private Long id;

        // Title should not be null
        // Title should have at least two characters
        @NotEmpty
        @Size(min=2 , message = "Post title should have at least 2 characters")
        private String title;

        // Post Description should not be null
        // post description should have at least 10 characters
        @NotEmpty
        @Size(min=10, message = "Post Description should have at least 10 characters")
        private String description;

        // Post Content not be null
        @NotEmpty
        private String content;
        private Set<CommentDto> comments;

}
