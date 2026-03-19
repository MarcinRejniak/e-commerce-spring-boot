package com.example.ecommerce.service;

import com.example.ecommerce.model.ProductCategory;
import com.example.ecommerce.repository.ProductCategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ProductCategoryServiceTest {

    @Mock
    private ProductCategoryRepository productCategoryRepository;

    @InjectMocks
    private ProductCategoryService productCategoryService;

    @Test
    void should_return_product_category_when_product_category_is_found() {
//        given
        Long productCategoryId = 1L;
        ProductCategory productCategory = new ProductCategory();
        productCategory.setId(productCategoryId);

        given(productCategoryRepository.findById(productCategoryId)).willReturn(Optional.of(productCategory));

//        when
        ProductCategory returnedProductCategory = productCategoryService.getProductCategory(productCategoryId);

//        then
        assertThat(returnedProductCategory).isNotNull();
        assertThat(returnedProductCategory.getId()).isEqualTo(productCategoryId);
    }

    @Test
    void should_throw_exception_when_product_category_is_not_found() {
//        given
        Long productCategoryId = 1L;
        ProductCategory productCategory = new ProductCategory();
        productCategory.setId(productCategoryId);

        given(productCategoryRepository.findById(productCategoryId)).willReturn(Optional.empty());

//        when
//        then
        assertThatThrownBy(() -> productCategoryService.getProductCategory(productCategoryId))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("Product category  not found, id: [1]");
    }
}
