package com.example.ecommerce.webclient.altoal.dto.countryfulldetails;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class AltoalGovernmentDto {

    @JsonProperty("administrative_divisions")
    private AltoalAdminDivisionsDto administrativeDivisions;
}
