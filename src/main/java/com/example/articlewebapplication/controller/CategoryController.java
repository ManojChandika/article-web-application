package com.example.articlewebapplication.controller;

import com.example.articlewebapplication.dto.CategoryDto;
import com.example.articlewebapplication.service.CategoryService;
import jakarta.persistence.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    // Create category
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto){
        return new ResponseEntity<>(categoryService.addCategoryDto(categoryDto), HttpStatus.CREATED);
    }

    //Get Category by id
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable(name = "id") Long categoryId){
        return ResponseEntity.ok(categoryService.getCategory(categoryId));
    }

    // Get All Categories
    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories(){
        return ResponseEntity.ok(categoryService.getAllCategory());
    }

    // Update Category
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryDto> updateCategory(
            @PathVariable(name = "id") Long categoryId,
            @RequestBody CategoryDto categoryDto
    ){
        return ResponseEntity.ok(categoryService.updateCategory(categoryId,categoryDto));
    }

    // Delete Category by id
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") Long categoryId){
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.ok("Category deleted successfully.!");
    }
}
