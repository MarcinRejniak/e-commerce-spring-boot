package com.example.ecommerce.mapper;

import com.example.ecommerce.dto.ProductCategoryDto;
import com.example.ecommerce.model.ProductCategory;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductCategoryMapper {

    ProductCategoryDto map(ProductCategory productCategory);
    ProductCategory map(ProductCategoryDto productCategoryDto);
}
