package com.example.ecommerce.controller;

import com.example.ecommerce.model.ProductCategory;
import com.example.ecommerce.service.ProductCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/product-categories")
@RequiredArgsConstructor
public class ProductCategoryController {

    private final ProductCategoryService productCategoryService;

    @GetMapping("/{id}")
    public ProductCategory getProductCategory(@PathVariable Long id) {
        return productCategoryService.getProductCategory(id);
    }

    @GetMapping
    public List<ProductCategory> getProductCategories() {
        return productCategoryService.getProductCategories();
    }
}
