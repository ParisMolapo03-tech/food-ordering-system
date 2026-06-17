package com.jumpstart.foodorderingsystem.service;

import com.jumpstart.foodorderingsystem.dto.CategoryDto;
import java.util.List;

public interface CategoryService {

    // Get all categories
    List<CategoryDto> getAllCategories();

    // Get one category by id
    CategoryDto getCategoryById(Long id);

    // Create a new category
    CategoryDto addCategory(CategoryDto dto);
}