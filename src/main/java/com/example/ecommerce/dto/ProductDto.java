package com.example.ecommerce.dto;

import java.math.BigDecimal;
import java.util.Date;

public record ProductDto (
        Long id,
        ProductCategoryDto category,
        String sku,
        String name,
        String description,
        BigDecimal unitPrice,
        String imageUrl,
        boolean active,
        int unitsInStock,
        Date dateCreated,
        Date lastUpdated
) {}
