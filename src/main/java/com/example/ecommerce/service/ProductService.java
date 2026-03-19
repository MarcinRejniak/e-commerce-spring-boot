package com.example.ecommerce.service;

import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.model.Product;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product getProduct(Long id) {
        return productRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Product not found, id: [%s]".formatted(id)));
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }
}
