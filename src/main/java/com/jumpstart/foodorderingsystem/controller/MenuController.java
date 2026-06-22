package com.jumpstart.foodorderingsystem.controller;

import com.jumpstart.foodorderingsystem.dto.MenuDto;
import com.jumpstart.foodorderingsystem.service.MenuService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @PostMapping
    public ResponseEntity<MenuDto> create(@RequestBody @Valid MenuDto dto) {
        MenuDto createdMenu = menuService.createMenu(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMenu);
    }

    @GetMapping
    public ResponseEntity<List<MenuDto>> getAll() {
        return ResponseEntity.ok(menuService.getAllMenus());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(menuService.getMenuById(id));
    }
}