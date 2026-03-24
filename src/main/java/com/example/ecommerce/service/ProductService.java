package com.example.ecommerce.service;

import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.model.Product;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product getProduct(Long id) {
        return productRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Product not found, id: [%s]".formatted(id)));
    }

    public Page<Product> getProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Page<Product> getProductsByCategoryId(Long categoryId, Pageable pageable) {
        return productRepository.findByCategoryId(categoryId, pageable);
    }
}
