package com.jumpstart.foodorderingsystem.service;

import com.jumpstart.foodorderingsystem.dto.LoginRequest;
import com.jumpstart.foodorderingsystem.dto.LoginResponse;
import com.jumpstart.foodorderingsystem.dto.RegisterRequest;
import com.jumpstart.foodorderingsystem.response.Response;

public interface AuthService {

    Response<String> register(RegisterRequest request);

    Response<LoginResponse> login(LoginRequest request);
}