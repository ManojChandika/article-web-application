package com.example.articlewebapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private Long id;
    // Name should not be null or empty
    @NotEmpty(message = "Name should not be null or empty")
    private String name;

    // Email Should not be null or empty
    // Email field validation
    @NotEmpty(message = "Email should not be null or empty")
    @Email
    private String email;
    // Email Should not be null or empty
    // Comment Body must be minimum 10 characters
    @NotEmpty
    @Size(min=10,message = "Comment Body must be minimum 10 characters")
    private String body;
}
