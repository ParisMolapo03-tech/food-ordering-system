package com.jumpstart.foodorderingsystem.dto;

// DTO stands for Data Transfer Object.
// Instead of sending the full entity to the client,
// we use a DTO to control exactly what data goes out.
// This protects sensitive fields from being exposed.
public class CategoryDto {

    // The id of the category
    private Long id;

    // The name of the category
    private String name;

    // Getters and Setters
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