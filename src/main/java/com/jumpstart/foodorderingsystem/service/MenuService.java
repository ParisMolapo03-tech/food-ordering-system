package com.jumpstart.foodorderingsystem.service;

import com.jumpstart.foodorderingsystem.dto.MenuDto;
import java.util.List;

// This interface defines the contract for the menu service layer.
public interface MenuService {

    // Create a new menu item
    MenuDto createMenu(MenuDto dto);

    // Get all menu items
    List<MenuDto> getAllMenus();

    // Get one menu item by id
    MenuDto getMenuById(Long id);
}