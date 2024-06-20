package com.example.newspapermanagment.service;

import com.example.newspapermanagment.dto.request.UserLoginRequest;
import com.example.newspapermanagment.dto.request.UserRegisterRequest;
import com.example.newspapermanagment.dto.response.UserResponse;

public interface UserService {
    UserResponse save(UserRegisterRequest userRegisterRequest);

    UserResponse auth(UserLoginRequest userRequest);

    void delete(Long id);
}
