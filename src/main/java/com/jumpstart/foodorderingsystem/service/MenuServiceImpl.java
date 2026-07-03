package com.jumpstart.foodorderingsystem.service;

import com.jumpstart.foodorderingsystem.dto.MenuDto;
import com.jumpstart.foodorderingsystem.entity.Category;
import com.jumpstart.foodorderingsystem.entity.Menu;
import com.jumpstart.foodorderingsystem.exception.CategoryNotFoundException;
import com.jumpstart.foodorderingsystem.exception.MenuNotFoundException;
import com.jumpstart.foodorderingsystem.repository.CategoryRepository;
import com.jumpstart.foodorderingsystem.repository.MenuRepository;
import com.jumpstart.foodorderingsystem.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Response<MenuDto> createMenu(MenuDto dto) {
        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException("Category not found with id: " + dto.getCategoryId()));

        Menu menu = mapToEntity(dto, category);
        Menu saved = menuRepository.save(menu);
        return Response.success("Menu created successfully", mapToDto(saved));
    }

    @Override
    public Response<Page<MenuDto>> getAllMenus(Long categoryId, String search, Pageable pageable) {
        Page<Menu> menus;

        if (categoryId != null && search != null) {
            menus = menuRepository.findByCategoryIdAndNameContainingIgnoreCase(categoryId, search, pageable);
        } else if (categoryId != null) {
            menus = menuRepository.findByCategoryId(categoryId, pageable);
        } else if (search != null) {
            menus = menuRepository.findByNameContainingIgnoreCase(search, pageable);
        } else {
            menus = menuRepository.findAll(pageable);
        }

        return Response.success("Menus retrieved", menus.map(this::mapToDto));
    }

    @Override
    public Response<MenuDto> getMenuById(Long id) {
        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> new MenuNotFoundException("Menu not found with id: " + id));
        return Response.success("Menu retrieved", mapToDto(menu));
    }

    @Override
    public Response<MenuDto> updateMenu(Long id, MenuDto dto) {
        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> new MenuNotFoundException("Menu not found with id: " + id));

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException("Category not found with id: " + dto.getCategoryId()));

        menu.setName(dto.getName());
        menu.setDescription(dto.getDescription());
        menu.setPrice(dto.getPrice());
        menu.setImageUrl(dto.getImageUrl());
        menu.setCategory(category);

        Menu updated = menuRepository.save(menu);
        return Response.success("Menu updated successfully", mapToDto(updated));
    }

    @Override
    public Response<String> deleteMenu(Long id) {
        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> new MenuNotFoundException("Menu not found with id: " + id));
        menuRepository.delete(menu);
        return Response.success("Menu deleted successfully", "Deleted menu with id: " + id);
    }

    // ---- Private helper methods ----

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