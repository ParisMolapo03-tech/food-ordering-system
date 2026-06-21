package com.jumpstart.foodorderingsystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonInclude;
// DTO stands for Data Transfer Object.
// Controls what data goes in and out of the API.
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryDto {

    private Long id;

    @NotBlank(message = "Category name is required")
    @Size(min = 2, max = 50, message = "Name must be 2-50 characters")
    private String name;

    // Description is optional
    private String description;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}