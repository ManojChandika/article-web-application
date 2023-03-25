package com.example.articlewebapplication.service;

import com.example.articlewebapplication.dto.AuthDto;
import com.example.articlewebapplication.dto.RegisterDto;

public interface AuthService {
    String login(AuthDto authDto);
    String register(RegisterDto registerDto);
}
