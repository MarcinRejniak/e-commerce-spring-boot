package com.example.ecommerce.controller;

import com.example.ecommerce.dto.ProductCategoryDto;
import com.example.ecommerce.service.ProductCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping(path = "/api/product-categories")
@RequiredArgsConstructor
public class ProductCategoryController {

    private final ProductCategoryService productCategoryService;

    @GetMapping("/{id}")
    public ProductCategoryDto getProductCategory(@PathVariable Long id) {
        return productCategoryService.getProductCategory(id);
    }

    @GetMapping
    public List<ProductCategoryDto> getProductCategories() {
        return productCategoryService.getProductCategories();
    }
}
