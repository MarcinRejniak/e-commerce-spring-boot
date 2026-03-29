package com.example.ecommerce.webclient.altoal;

import com.example.ecommerce.dto.CountryDto;
import com.example.ecommerce.dto.StateDto;
import com.example.ecommerce.webclient.altoal.dto.country.AltoalCountryDto;
import com.example.ecommerce.webclient.altoal.dto.countryfulldetails.AltoalCountryFullDetailsDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
public class AltoalCountryClient {

    private static final String BASE_URL = "https://countries.altoal.com/api/v1/";
    private final RestTemplate restTemplate = new RestTemplate();

    public List<CountryDto> getCountries() {
        AltoalCountryDto response = getMethod("metadata.json", AltoalCountryDto.class);

        return response
                .getCountries()
                .entrySet()
                .stream()
                .map(entry -> CountryDto
                        .builder()
                        .name(entry.getValue().getName())
                        .slug(entry.getKey())
                        .build()
                )
                .toList();
    }

    public List<StateDto> getStates(String country) {
        AltoalCountryFullDetailsDto response =
                getMethod("/name/{country}.json", AltoalCountryFullDetailsDto.class, country);

        if (response == null ||
                response.getData().getGovernment().getAdministrative_divisions() == null ||
                response.getData().getGovernment().getAdministrative_divisions().getValue() == null ||
                response.getData().getGovernment().getAdministrative_divisions().getValue().getDivisions() == null
        ) {
            return Collections.emptyList();
        }

        return response
                .getData()
                .getGovernment()
                .getAdministrative_divisions()
                .getValue()
                .getDivisions()
                .stream()
                .map(this::mapToStateDto)
                .filter(Objects::nonNull)
                .toList();
    }

    private StateDto mapToStateDto(Object item) {
        if (item instanceof String name) {
            return StateDto.builder().name(name).build();
        }
        if (item instanceof Map<?, ?> map && map.get("string") instanceof String nameVal) {
            return StateDto.builder().name(nameVal).build();
        }
        return null;
    }

    public <T> T getMethod(String url, Class<T> responseType, Object... objects) {
        return restTemplate.getForObject(BASE_URL + url,
                responseType, objects);
    }
}
