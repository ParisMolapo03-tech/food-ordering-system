package com.jumpstart.foodorderingsystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

// DTO stands for Data Transfer Object.
// Instead of sending the full entity to the client,
// we use a DTO to control exactly what data goes out.
// This protects sensitive fields from being exposed.
public class CategoryDto {

    private Long id;

    // Validation rules for name field
    @NotBlank(message = "Category name is required")
    @Size(min = 2, max = 50, message = "Name must be 2-50 characters")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}