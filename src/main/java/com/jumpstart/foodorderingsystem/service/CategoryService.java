package com.jumpstart.foodorderingsystem.service;

import com.jumpstart.foodorderingsystem.dto.CategoryDto;
import com.jumpstart.foodorderingsystem.response.Response;

import java.util.List;

public interface CategoryService {

    Response<List<CategoryDto>> getAllCategories();

    Response<CategoryDto> getCategoryById(Long id);

    Response<CategoryDto> addCategory(CategoryDto dto);

    Response<CategoryDto> updateCategory(Long id, CategoryDto dto);

    Response<String> deleteCategory(Long id);
}