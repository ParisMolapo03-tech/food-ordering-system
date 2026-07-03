package com.jumpstart.foodorderingsystem.service;

import com.jumpstart.foodorderingsystem.dto.CategoryDto;
import com.jumpstart.foodorderingsystem.entity.Category;
import com.jumpstart.foodorderingsystem.exception.CategoryNotFoundException;
import com.jumpstart.foodorderingsystem.repository.CategoryRepository;
import com.jumpstart.foodorderingsystem.repository.MenuRepository;
import com.jumpstart.foodorderingsystem.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final MenuRepository menuRepository;

    @Override
    public Response<List<CategoryDto>> getAllCategories() {
        List<CategoryDto> categories = categoryRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .toList();
        return Response.success("Categories retrieved", categories);
    }

    @Override
    public Response<CategoryDto> getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found with id: " + id));
        return Response.success("Category retrieved", mapToDto(category));
    }

    @Override
    public Response<CategoryDto> addCategory(CategoryDto dto) {
        Category category = new Category();
        category.setName(dto.getName());
        category.setDescription(dto.getDescription());
        Category saved = categoryRepository.save(category);
        return Response.success("Category created successfully", mapToDto(saved));
    }

    @Override
    public Response<CategoryDto> updateCategory(Long id, CategoryDto dto) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found with id: " + id));
        category.setName(dto.getName());
        category.setDescription(dto.getDescription());
        Category updated = categoryRepository.save(category);
        return Response.success("Category updated successfully", mapToDto(updated));
    }

    @Override
    public Response<String> deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found with id: " + id));

        if (menuRepository.existsByCategoryId(id)) {
            throw new DataIntegrityViolationException("Cannot delete category — it still has menus linked to it");
        }

        categoryRepository.delete(category);
        return Response.success("Category deleted successfully", "Deleted category with id: " + id);
    }

    private CategoryDto mapToDto(Category category) {
        CategoryDto dto = new CategoryDto();
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setDescription(category.getDescription());
        return dto;
    }
}