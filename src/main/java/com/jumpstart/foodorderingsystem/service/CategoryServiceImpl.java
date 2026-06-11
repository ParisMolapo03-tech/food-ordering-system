package com.jumpstart.foodorderingsystem.service;

import com.jumpstart.foodorderingsystem.dto.CategoryDto;
import com.jumpstart.foodorderingsystem.entity.Category;
import com.jumpstart.foodorderingsystem.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

// This class implements the CategoryService interface.
// It contains the actual business logic.
// It fetches data from the database and converts
// Category entities into CategoryDto objects.
@Service
public class CategoryServiceImpl implements CategoryService {

    // CategoryRepository is injected here using Dependency Injection
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDto> getAllCategories() {

        // Fetch all categories from the database
        List<Category> categories = categoryRepository.findAll();

        // Convert each Category entity into a CategoryDto
        return categories.stream().map(category -> {
            CategoryDto dto = new CategoryDto();
            dto.setId(category.getId());
            dto.setName(category.getName());
            return dto;
        }).collect(Collectors.toList());
    }
}