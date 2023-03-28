package com.example.articlewebapplication.repo;

import com.example.articlewebapplication.entity.Category;
import com.example.articlewebapplication.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepo extends JpaRepository<Category,Long> {

}
