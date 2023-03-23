package com.example.articlewebapplication.repo;

import com.example.articlewebapplication.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepo extends JpaRepository<Post,Long> {
}
