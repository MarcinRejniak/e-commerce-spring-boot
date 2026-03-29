package com.example.ecommerce.webclient.altoal;

import com.example.ecommerce.dto.CountryDto;
import com.example.ecommerce.dto.StateDto;
import com.example.ecommerce.webclient.altoal.dto.country.AltoalCountryDto;
import com.example.ecommerce.webclient.altoal.dto.countryfulldetails.AltoalCountryFullDetailsDto;
import com.example.ecommerce.webclient.altoal.dto.countryfulldetails.AltoalStateDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

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
                .map(entry -> new CountryDto(
                        entry.getValue().getName(),
                        entry.getKey()))
                .toList();
    }

    public StateDto getStates(String country) {
        AltoalCountryFullDetailsDto response =
                getMethod("/name/{country}.json", AltoalCountryFullDetailsDto.class, country);

        List<String> divisions = response
                .getData()
                .getGovernment()
                .getAdministrative_divisions()
                .getValue()
                .getDivisions()
                .stream()
                .map(AltoalStateDto::getString)
                .toList();

        return StateDto.builder()
                .states(divisions)
                .build();
    }

    public <T> T getMethod(String url, Class<T> responseType, Object... objects) {
        return restTemplate.getForObject(BASE_URL + url,
                responseType, objects);
    }
}
