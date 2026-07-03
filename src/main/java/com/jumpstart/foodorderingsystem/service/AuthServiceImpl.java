package com.jumpstart.foodorderingsystem.service;

import com.jumpstart.foodorderingsystem.config.JwtUtils;
import com.jumpstart.foodorderingsystem.dto.LoginRequest;
import com.jumpstart.foodorderingsystem.dto.LoginResponse;
import com.jumpstart.foodorderingsystem.dto.RegisterRequest;
import com.jumpstart.foodorderingsystem.entity.Role;
import com.jumpstart.foodorderingsystem.entity.User;
import com.jumpstart.foodorderingsystem.repository.RoleRepository;
import com.jumpstart.foodorderingsystem.repository.UserRepository;
import com.jumpstart.foodorderingsystem.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    @Override
    public Response<String> register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        Role customerRole = roleRepository.findByName("CUSTOMER")
                .orElseThrow(() -> new RuntimeException("CUSTOMER role not found"));

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setPhoneNumber(request.getPhoneNumber());
        user.setAddress(request.getAddress());
        user.setActive(true);
        user.setRoles(List.of(customerRole));

        userRepository.save(user);

        return Response.success("Registration successful", "User registered: " + request.getEmail());
    }

    @Override
    public Response<LoginResponse> login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!user.isActive()) {
            throw new RuntimeException("Account inactive. Please contact support");
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtUtils.generateToken(user.getEmail());

        List<String> roles = user.getRoles()
                .stream()
                .map(Role::getName)
                .toList();

        LoginResponse loginResponse = new LoginResponse(token, user.getEmail(), user.getName(), roles);

        return Response.success("Login successful", loginResponse);
    }
}