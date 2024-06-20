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

@RestController(value = "/users")
@Tag(name = "Clients")
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @Operation(summary = "This method is used to register the clients.")
    @PostMapping("/register")
    public ResponseEntity<UserResponse> register( UserRegisterRequest userRegisterRequest) throws IOException {
        return ResponseEntity.ok(userService.save(userRegisterRequest));
    }
    @Operation(summary = "This method is used to login the clients.")
    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody UserLoginRequest userRequest){
        return ResponseEntity.ok(userService.auth(userRequest));
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        userService.delete(id);
    }
}

