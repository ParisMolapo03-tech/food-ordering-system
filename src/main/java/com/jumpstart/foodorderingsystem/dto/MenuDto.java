package com.jumpstart.foodorderingsystem.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

// DTO for Menu - controls what data goes in and out of the API.
// categoryId is used for input, categoryName is used for output.
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MenuDto {

    private Long id;

    // Name is required
    @NotBlank(message = "Menu name is required")
    private String name;

    private String description;

    // Price is required and must be 0 or above
    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", message = "Price must be greater than or equal to 0")
    private BigDecimal price;

    private String imageUrl;

    // Used for input — which category does this menu belong to?
    @NotNull(message = "Category id is required")
    private Long categoryId;

    // Used for output — shows the category name in the response
    private String categoryName;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public Long getCategoryId() { return categoryId; }
    public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }

    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
}