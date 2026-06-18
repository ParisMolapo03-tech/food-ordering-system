package com.jumpstart.foodorderingsystem.service;

import com.jumpstart.foodorderingsystem.dto.CategoryDto;
import com.jumpstart.foodorderingsystem.entity.Category;
import com.jumpstart.foodorderingsystem.exception.CategoryNotFoundException;
import com.jumpstart.foodorderingsystem.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

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
            dto.setDescription(category.getDescription());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public CategoryDto getCategoryById(Long id) {
        Optional<Category> optional = categoryRepository.findById(id);
        if (optional.isEmpty()) {
            throw new CategoryNotFoundException("Category with id " + id + " not found");
        }
        Category category = optional.get();
        CategoryDto dto = new CategoryDto();
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setDescription(category.getDescription());
        return dto;
    }

    @Override
    public CategoryDto addCategory(CategoryDto dto) {
        Category category = new Category();
        category.setName(dto.getName());
        category.setDescription(dto.getDescription());
        Category saved = categoryRepository.save(category);
        CategoryDto savedDto = new CategoryDto();
        savedDto.setId(saved.getId());
        savedDto.setName(saved.getName());
        savedDto.setDescription(saved.getDescription());
        return savedDto;
    }

    @Override
    public CategoryDto updateCategory(Long id, CategoryDto dto) {
        Optional<Category> optional = categoryRepository.findById(id);
        if (optional.isEmpty()) {
            throw new CategoryNotFoundException("Category with id " + id + " not found");
        }
        Category category = optional.get();
        category.setName(dto.getName());
        category.setDescription(dto.getDescription());
        Category updated = categoryRepository.save(category);
        CategoryDto updatedDto = new CategoryDto();
        updatedDto.setId(updated.getId());
        updatedDto.setName(updated.getName());
        updatedDto.setDescription(updated.getDescription());
        return updatedDto;
    }

    @Override
    public void deleteCategory(Long id) {
        Optional<Category> optional = categoryRepository.findById(id);
        if (optional.isEmpty()) {
            throw new CategoryNotFoundException("Category with id " + id + " not found");
        }
        categoryRepository.deleteById(id);
    }
}