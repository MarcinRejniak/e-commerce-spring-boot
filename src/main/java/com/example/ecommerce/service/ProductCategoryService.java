package com.example.ecommerce.service;

import com.example.ecommerce.repository.ProductCategoryRepository;
import com.example.ecommerce.model.ProductCategory;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductCategoryService {

    private final ProductCategoryRepository productCategoryRepository;

    public ProductCategory getProductCategory(Long id) {
        return productCategoryRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Product category  not found, id: [%s]".formatted(id)));
    }

    public List<ProductCategory> getProductCategories() {
        return productCategoryRepository.findAll();
    }
}
