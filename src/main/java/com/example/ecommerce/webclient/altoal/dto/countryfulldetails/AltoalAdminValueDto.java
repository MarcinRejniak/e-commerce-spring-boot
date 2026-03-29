package com.example.ecommerce.webclient.altoal.dto.countryfulldetails;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;

import java.util.List;

@Getter
public class AltoalAdminValueDto {

    @JsonAlias({"states", "provinces", "governorates", "parishes", "districts", "departments", "regions",
            "municipalities", "prefectures", "counties", "administrative_precincts", "islands", "two_tier_counties",
            "emirates", "island_councils", "island_divisions", "cantons", "autonomous_communities", "oblasts",
            "administrative_districts", "communes", "administrative_areas", "villages", "quarters",
            "administrative_atolls", "administrative_regions", "subdivisions", "regional_states",
            "urban_municipalities"})
    private List<Object> divisions;
}
