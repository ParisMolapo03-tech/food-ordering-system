package com.jumpstart.foodorderingsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

// This class represents the menus table in the database.
// Each menu item belongs to one category - @ManyToOne relationship.
@Entity
@Table(name = "menus")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Menu {

    // Auto-generated primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Name of the menu item
    private String name;

    // Description - allows up to 1000 characters
    @Column(length = 1000)
    private String description;

    // Price stored as BigDecimal for accuracy
    private BigDecimal price;

    // Image URL for the menu item
    private String imageUrl;

    // Many menus belong to one category
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}