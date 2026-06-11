package com.jumpstart.foodorderingsystem.repository;

import com.jumpstart.foodorderingsystem.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

// The repository is responsible for talking to the database.
// By extending JpaRepository we get all basic database operations
// like save, delete, findAll and findById for free
// without writing any SQL manually.
public interface CategoryRepository extends JpaRepository<Category, Long> {

}