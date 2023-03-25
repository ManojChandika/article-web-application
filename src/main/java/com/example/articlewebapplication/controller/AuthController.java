package com.example.articlewebapplication.controller;

import com.example.articlewebapplication.dto.AuthDto;
import com.example.articlewebapplication.dto.RegisterDto;
import com.example.articlewebapplication.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")

public class AuthController {
    @Autowired
    private AuthService authService;

    // Build login Rest API
    @PostMapping(value = {"/login", "/signin"})
    public ResponseEntity<String> login(@RequestBody AuthDto authDto){
        return ResponseEntity.ok(authService.login(authDto));
    }

    // Build Register Rest API
    @PostMapping(value = {"/register", "/signup"})
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
        return new ResponseEntity<>(authService.register(registerDto), HttpStatus.CREATED);
    }

}
