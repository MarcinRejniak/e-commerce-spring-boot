package com.example.ecommerce.service;

import com.example.ecommerce.dto.ProductDto;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.repository.ProductRepository;
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
class ProductServiceTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductService productService;

    @Test
    void should_return_product_when_product_is_found() {

//        given
        Long productId = 1L;
        Product product = new Product();
        product.setId(productId);

        given(productRepository.findById(productId)).willReturn(Optional.of(product));

//        when
        ProductDto result = productService.getProduct(productId);

//        then
        assertThat(result).isNotNull();
        assertThat(result.id()).isEqualTo(productId);
    }

    @Test
    void should_throw_exception_when_product_id_is_not_found() {

//        given
        Long productId = 1L;
        Product product = new Product();
        product.setId(productId);

        given(productRepository.findById(productId)).willReturn(Optional.empty());

//        when
//        then
        assertThatThrownBy(() -> productService.getProduct(productId))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("Product not found, id: [1]");
    }
}
