package com.jumpstart.foodorderingsystem.controller;

import com.jumpstart.foodorderingsystem.dto.CategoryDto;
import com.jumpstart.foodorderingsystem.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

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

    // POST /api/categories - creates a new category
    @PostMapping
    public ResponseEntity<CategoryDto> addCategory(@RequestBody @Valid CategoryDto dto) {
        CategoryDto saved = categoryService.addCategory(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // PUT /api/categories/{id} - updates an existing category
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable Long id, @RequestBody @Valid CategoryDto dto) {
        CategoryDto updated = categoryService.updateCategory(id, dto);
        return ResponseEntity.ok(updated);
    }

    // DELETE /api/categories/{id} - deletes a category
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}