package com.jumpstart.foodorderingsystem.service;

import com.jumpstart.foodorderingsystem.dto.CategoryDto;
import com.jumpstart.foodorderingsystem.entity.Category;
import com.jumpstart.foodorderingsystem.exception.CategoryNotFoundException;
import com.jumpstart.foodorderingsystem.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// This class contains the actual business logic.
// It fetches data from the database and converts
// Category entities into CategoryDto objects.
@Service
public class CategoryServiceImpl implements CategoryService {

    // CategoryRepository injected using Dependency Injection
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(category -> {
            CategoryDto dto = new CategoryDto();
            dto.setId(category.getId());
            dto.setName(category.getName());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public CategoryDto getCategoryById(Long id) {
        // Find category or throw exception if not found
        Optional<Category> optional = categoryRepository.findById(id);
        if (optional.isEmpty()) {
            throw new CategoryNotFoundException("Category with id " + id + " not found");
        }
        Category category = optional.get();
        CategoryDto dto = new CategoryDto();
        dto.setId(category.getId());
        dto.setName(category.getName());
        return dto;
    }
}