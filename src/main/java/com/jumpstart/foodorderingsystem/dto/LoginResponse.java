package com.jumpstart.foodorderingsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class LoginResponse {

    private String token;
    private String email;
    private String name;
    private List<String> roles;
}