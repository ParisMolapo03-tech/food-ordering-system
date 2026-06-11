package com.jumpstart.foodorderingsystem.entity;

import jakarta.persistence.*;

// This class represents the category table in the database.
// Each field maps to a column in the table.
@Entity
@Table(name = "category")
public class Category {

    // This is the primary key - it auto increments
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // This is the name of the category e.g. Fast Food, Pizza
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