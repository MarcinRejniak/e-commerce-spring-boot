package com.example.ecommerce.service;

import com.example.ecommerce.dto.ProductCategoryDto;
import com.example.ecommerce.mapper.ProductCategoryMapper;
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
    private final ProductCategoryMapper productCategoryMapper;

    public ProductCategoryDto getProductCategory(Long id) {
        ProductCategory productCategory = productCategoryRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Product category  not found, id: [%s]".formatted(id)));

        return productCategoryMapper.map(productCategory);
    }

    public List<ProductCategoryDto> getProductCategories() {
        List<ProductCategory> productCategories = productCategoryRepository.findAll();

        return productCategories.stream().map(productCategoryMapper::map).toList();
    }
}
