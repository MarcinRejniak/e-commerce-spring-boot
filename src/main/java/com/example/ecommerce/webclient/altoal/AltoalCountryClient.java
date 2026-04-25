package com.example.ecommerce.webclient.altoal;

import com.example.ecommerce.dto.CountryDto;
import com.example.ecommerce.dto.StateDto;
import com.example.ecommerce.webclient.altoal.dto.country.AltoalCountryDto;
import com.example.ecommerce.webclient.altoal.dto.countryfulldetails.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import java.util.*;

@Component
@RequiredArgsConstructor
public class AltoalCountryClient {

    private final RestClient restClient;

    public List<CountryDto> getCountries() {

        AltoalCountryDto response = restClient.get()
                .uri("metadata.json")
                .retrieve()
                .body(AltoalCountryDto.class);

        if (response == null || response.getCountries() == null) {
            return Collections.emptyList();
        }

        return response
                .getCountries()
                .entrySet()
                .stream()
                .map(entry -> CountryDto.builder()
                        .name(entry.getValue().getName())
                        .slug(entry.getKey())
                        .build()
                )
                .toList();
    }

    public List<StateDto> getStates(String country) {
        try {
            AltoalCountryFullDetailsDto response = restClient.get()
                    .uri("name/{country}.json", country)
                    .retrieve()
                    .body(AltoalCountryFullDetailsDto.class);

            return Optional.ofNullable(response)
                    .map(AltoalCountryFullDetailsDto::getData)
                    .map(AltoalCountryDataDto::getGovernment)
                    .map(AltoalGovernmentDto::getAdministrativeDivisions)
                    .map(AltoalAdminDivisionsDto::getValue)
                    .map(AltoalAdminValueDto::getDivisions)
                    .stream()
                    .flatMap(Collection::stream)
                    .map(this::mapToStateDto)
                    .filter(Objects::nonNull)
                    .toList();
        } catch (HttpClientErrorException e) {
            return Collections.emptyList();
        }

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
}
