package com.example.ecommerce.webclient.altoal.dto.countryfulldetails;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;

import java.util.List;

@Getter
public class AltoalAdminValueDto {

    @JsonAlias({"states", "provinces"})
    private List<AltoalStateDto> divisions;
}
