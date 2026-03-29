package com.example.ecommerce.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class StateDto {

    private List<String> states;
}
