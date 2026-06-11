package com.jumpstart.foodorderingsystem.controller;

import com.jumpstart.foodorderingsystem.dto.CategoryDto;
import com.jumpstart.foodorderingsystem.service.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

// The controller is the entry point of the application.
// It receives HTTP requests from the client (Postman or browser)
// and passes them to the service layer.
// It never talks to the database directly.
@RestController
@RequestMapping("/api/category")
public class CategoryController {

    // CategoryService is injected using Dependency Injection
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // This endpoint handles GET requests to /api/category
    // It returns all categories from the database as JSON
    @GetMapping
    public List<CategoryDto> getAllCategories() {
        return categoryService.getAllCategories();
    }
}