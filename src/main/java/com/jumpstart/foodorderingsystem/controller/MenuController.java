package com.jumpstart.foodorderingsystem.controller;

import com.jumpstart.foodorderingsystem.dto.MenuDto;
import com.jumpstart.foodorderingsystem.response.Response;
import com.jumpstart.foodorderingsystem.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/menu")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @PostMapping
    public ResponseEntity<Response<MenuDto>> create(@RequestBody @Valid MenuDto dto) {
        return ResponseEntity.ok(menuService.createMenu(dto));
    }

    @GetMapping
    public ResponseEntity<Response<Page<MenuDto>>> getAll(
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id,asc") String sort) {

        String[] sortParams = sort.split(",");
        String sortField = sortParams[0];
        Sort.Direction direction = sortParams.length > 1 && sortParams[1].equalsIgnoreCase("desc")
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortField));
        return ResponseEntity.ok(menuService.getAllMenus(categoryId, search, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<MenuDto>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(menuService.getMenuById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<MenuDto>> update(@PathVariable Long id, @RequestBody @Valid MenuDto dto) {
        return ResponseEntity.ok(menuService.updateMenu(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<String>> delete(@PathVariable Long id) {
        return ResponseEntity.ok(menuService.deleteMenu(id));
    }
}