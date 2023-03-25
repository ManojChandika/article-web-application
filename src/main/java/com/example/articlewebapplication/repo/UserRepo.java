package com.example.articlewebapplication.repo;

import com.example.articlewebapplication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Long> {

    Optional<User> findByUserName(String userName);
    Optional<User> findByEmail(String email);
    Optional<User> findByUserNameOrEmail(String userName, String email);
    boolean existsByUserName(String userName);
    boolean existsByEmail(String email);


}
