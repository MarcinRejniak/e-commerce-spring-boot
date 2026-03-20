package com.example.ecommerce.controller;

import com.example.ecommerce.model.ProductCategory;
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
    public ProductCategory getProductCategory(@PathVariable Long id) {
        return productCategoryService.getProductCategory(id);
    }

    @GetMapping
    public List<ProductCategory> getProductCategories() {
        return productCategoryService.getProductCategories();
    }
}
