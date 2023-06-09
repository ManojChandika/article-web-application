package com.example.articlewebapplication.repo;

import com.example.articlewebapplication.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Long> {
    List<Post>  findByCategoryId(Long categoryId);
}
