package com.jumpstart.foodorderingsystem.service;

import com.jumpstart.foodorderingsystem.dto.MenuDto;
import com.jumpstart.foodorderingsystem.entity.Category;
import com.jumpstart.foodorderingsystem.entity.Menu;
import com.jumpstart.foodorderingsystem.exception.CategoryNotFoundException;
import com.jumpstart.foodorderingsystem.repository.CategoryRepository;
import com.jumpstart.foodorderingsystem.repository.MenuRepository;
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
                .orElseThrow(() -> new CategoryNotFoundException(
                        "Category with id " + dto.getCategoryId() + " not found"));
        Menu menu = mapToEntity(dto, category);
        Menu saved = menuRepository.save(menu);
        return mapToDto(saved);
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
                .orElseThrow(() -> new CategoryNotFoundException(
                        "Menu with id " + id + " not found"));
        return mapToDto(menu);
    }

    private MenuDto mapToDto(Menu menu) {
        MenuDto dto = new MenuDto();
        dto.setId(menu.getId());
        dto.setName(menu.getName());
        dto.setDescription(menu.getDescription());
        dto.setPrice(menu.getPrice());
        dto.setImageUrl(menu.getImageUrl());
        dto.setCategoryId(menu.getCategory().getId());
        dto.setCategoryName(menu.getCategory().getName());
        return dto;
    }

    private Menu mapToEntity(MenuDto dto, Category category) {
        return Menu.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .imageUrl(dto.getImageUrl())
                .category(category)
                .build();
    }
}