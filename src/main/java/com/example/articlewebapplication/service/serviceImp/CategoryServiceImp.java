package com.example.articlewebapplication.service.serviceImp;

import com.example.articlewebapplication.dto.CategoryDto;
import com.example.articlewebapplication.dto.PostDto;
import com.example.articlewebapplication.entity.Category;
import com.example.articlewebapplication.entity.Post;
import com.example.articlewebapplication.exception.ResourceNotFoundException;
import com.example.articlewebapplication.repo.CategoryRepo;
import com.example.articlewebapplication.repo.PostRepo;
import com.example.articlewebapplication.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImp implements CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PostRepo postRepo;

    // Create category
    @Override
    public CategoryDto addCategoryDto(CategoryDto categoryDto) {
        return  modelMapper.map(categoryRepo.save(modelMapper.map(categoryDto, Category.class)),CategoryDto.class);
    }

    // Get category By id
    @Override
    public CategoryDto getCategory(Long categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category" , "id", categoryId));
        return modelMapper.map(category,CategoryDto.class);
    }

    // Get all categories
    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> categories = categoryRepo.findAll();

        return categories
                .stream().
                map(category -> modelMapper.map(category,CategoryDto.class))
                .collect(Collectors.toList());

    }

    // update category By id
    @Override
    public CategoryDto updateCategory(Long categoryId, CategoryDto categoryDto) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","id",categoryId ));
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());

        return modelMapper.map(categoryRepo.save(category),CategoryDto.class);
    }

    // Delete Category
    @Override
    public void deleteCategory(Long categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","id",categoryId));
        categoryRepo.delete(category);
    }

}
