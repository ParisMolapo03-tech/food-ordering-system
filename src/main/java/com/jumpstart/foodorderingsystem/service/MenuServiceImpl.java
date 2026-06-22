package com.jumpstart.foodorderingsystem.service.impl;

import com.jumpstart.foodorderingsystem.dto.MenuDto;
import com.jumpstart.foodorderingsystem.entity.Category;
import com.jumpstart.foodorderingsystem.entity.Menu;
import com.jumpstart.foodorderingsystem.repository.CategoryRepository;
import com.jumpstart.foodorderingsystem.repository.MenuRepository;
import com.jumpstart.foodorderingsystem.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public MenuDto createMenu(MenuDto dto) {

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Menu menu = new Menu();
        menu.setName(dto.getName());
        menu.setDescription(dto.getDescription());
        menu.setPrice(dto.getPrice());
        menu.setImageUrl(dto.getImageUrl());
        menu.setCategory(category);

        Menu savedMenu = menuRepository.save(menu);

        return mapToDto(savedMenu);
    }

    @Override
    public List<MenuDto> getAllMenus() {
        return menuRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    @Override
    public MenuDto getMenuById(Long id) {

        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu not found"));

        return mapToDto(menu);
    }

    private MenuDto mapToDto(Menu menu) {

        MenuDto dto = new MenuDto();

        dto.setId(menu.getId());
        dto.setName(menu.getName());
        dto.setDescription(menu.getDescription());
        dto.setPrice(menu.getPrice());
        dto.setImageUrl(menu.getImageUrl());

        if (menu.getCategory() != null) {
            dto.setCategoryId(menu.getCategory().getId());
            dto.setCategoryName(menu.getCategory().getName());
        }

        return dto;
    }
}