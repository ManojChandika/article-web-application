package com.example.articlewebapplication.service.serviceImp;

import com.example.articlewebapplication.dto.AuthDto;
import com.example.articlewebapplication.dto.RegisterDto;
import com.example.articlewebapplication.entity.Role;
import com.example.articlewebapplication.entity.User;
import com.example.articlewebapplication.exception.BlogApiException;
import com.example.articlewebapplication.repo.RoleRepo;
import com.example.articlewebapplication.repo.UserRepo;
import com.example.articlewebapplication.service.AuthService;
import com.example.articlewebapplication.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImp implements AuthService {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String login(AuthDto authDto) {
        Authentication authentication=authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authDto.getUserNameOrEmail(),authDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtUtil.generateToken(authentication);

        return token;
    }

    @Override
    public String register(RegisterDto registerDto) {

        // Check userName exit in database
        if(userRepo.existsByUserName(registerDto.getUserName())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST,"user name already exit!");
        }

        // check Email exit in database
        if(userRepo.existsByEmail(registerDto.getEmail())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST,"Email already exit!");
        }

        User user=new User();
        user.setName(registerDto.getName());
        user.setEmail(registerDto.getEmail());
        user.setUserName(registerDto.getUserName());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepo.findByName("ROLE_USER").get();
        roles.add(userRole);
        user.setRoles(roles);

        userRepo.save(user);

        return "User Registered Successfully!";

    }
}
