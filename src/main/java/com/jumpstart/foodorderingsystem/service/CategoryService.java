package com.jumpstart.foodorderingsystem.service;

import com.jumpstart.foodorderingsystem.dto.CategoryDto;
import java.util.List;

// This interface defines the contract for the service layer.
// Any class that implements this must provide
// a way to get all categories.
public interface CategoryService {

    // This method will return a list of all categories
    List<CategoryDto> getAllCategories();
}