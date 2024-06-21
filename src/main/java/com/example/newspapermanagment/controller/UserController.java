package com.example.newspapermanagment.controller;

import com.example.newspapermanagment.dto.request.UserLoginRequest;
import com.example.newspapermanagment.dto.request.UserRegisterRequest;
import com.example.newspapermanagment.dto.response.UserResponse;
import com.example.newspapermanagment.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@Tag(name = "Authentication")
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping("/register")
    public ResponseEntity<UserResponse> register( UserRegisterRequest userRegisterRequest) throws IOException {
        return ResponseEntity.ok(userService.save(userRegisterRequest));
    }
    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody UserLoginRequest userRequest){
        return ResponseEntity.ok(userService.auth(userRequest));
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        userService.delete(id);
    }
}

