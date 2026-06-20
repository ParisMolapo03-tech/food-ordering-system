package com.jumpstart.foodorderingsystem.controller;

import com.jumpstart.foodorderingsystem.dto.CategoryDto;
import com.jumpstart.foodorderingsystem.response.Response;
import com.jumpstart.foodorderingsystem.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

// The controller receives HTTP requests and passes them to the service.
// Every response is now wrapped in Response<T> for consistency.
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // GET /api/categories - returns all categories
    @GetMapping
    public ResponseEntity<Response<List<CategoryDto>>> getAllCategories() {
        List<CategoryDto> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(Response.success("Categories retrieved", categories));
    }

    // GET /api/categories/{id} - returns one category by id
    @GetMapping("/{id}")
    public ResponseEntity<Response<CategoryDto>> getCategoryById(@PathVariable Long id) {
        CategoryDto dto = categoryService.getCategoryById(id);
        return ResponseEntity.ok(Response.success("Category retrieved", dto));
    }

    // POST /api/categories - creates a new category
    @PostMapping
    public ResponseEntity<Response<CategoryDto>> addCategory(@RequestBody @Valid CategoryDto dto) {
        CategoryDto saved = categoryService.addCategory(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Response.success("Category created", saved));
    }

    // PUT /api/categories/{id} - updates an existing category
    @PutMapping("/{id}")
    public ResponseEntity<Response<CategoryDto>> updateCategory(
            @PathVariable Long id,
            @RequestBody @Valid CategoryDto dto) {
        CategoryDto updated = categoryService.updateCategory(id, dto);
        return ResponseEntity.ok(Response.success("Category updated", updated));
    }

    // DELETE /api/categories/{id} - deletes a category
    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Void>> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok(Response.success("Category deleted", null));
    }
}