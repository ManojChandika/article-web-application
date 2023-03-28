package com.example.articlewebapplication.service;

import com.example.articlewebapplication.dto.CategoryDto;
import com.example.articlewebapplication.dto.PostDto;
import com.example.articlewebapplication.entity.Category;
import com.example.articlewebapplication.entity.Post;

import java.util.List;

public interface CategoryService {
    CategoryDto addCategoryDto(CategoryDto categoryDto);
    CategoryDto getCategory(Long categoryId);
    List<CategoryDto> getAllCategory();
    CategoryDto updateCategory(Long categoryId, CategoryDto categoryDto);
    void deleteCategory(Long categoryId);

}
