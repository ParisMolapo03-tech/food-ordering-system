package com.jumpstart.foodorderingsystem.service;

import com.jumpstart.foodorderingsystem.dto.MenuDto;
import com.jumpstart.foodorderingsystem.response.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MenuService {

    Response<MenuDto> createMenu(MenuDto dto);

    Response<Page<MenuDto>> getAllMenus(Long categoryId, String search, Pageable pageable);

    Response<MenuDto> getMenuById(Long id);

    Response<MenuDto> updateMenu(Long id, MenuDto dto);

    Response<String> deleteMenu(Long id);
}