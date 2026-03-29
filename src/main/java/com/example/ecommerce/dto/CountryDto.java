package com.example.ecommerce.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CountryDto {

    private String name;
    private String slug;
}
