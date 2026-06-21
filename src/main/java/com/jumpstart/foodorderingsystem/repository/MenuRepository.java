package com.jumpstart.foodorderingsystem.repository;

import com.jumpstart.foodorderingsystem.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

// Repository for Menu entity.
// Extends JpaRepository to get free database operations
// like save, findAll, findById and deleteById.
public interface MenuRepository extends JpaRepository<Menu, Long> {

}