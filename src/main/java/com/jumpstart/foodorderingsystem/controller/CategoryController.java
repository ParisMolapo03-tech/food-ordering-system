package com.jumpstart.foodorderingsystem.controller;

import com.jumpstart.foodorderingsystem.dto.CategoryDto;
import com.jumpstart.foodorderingsystem.service.CategoryService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

// The controller receives HTTP requests and passes them to the service.
// It never talks to the database directly.
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    // CategoryService injected using Dependency Injection
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // GET /api/categories - returns all categories
    @GetMapping
    public List<CategoryDto> getAllCategories() {
        return categoryService.getAllCategories();
    }

    // GET /api/categories/{id} - returns one category by id
    @GetMapping("/{id}")
    public CategoryDto getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }
}