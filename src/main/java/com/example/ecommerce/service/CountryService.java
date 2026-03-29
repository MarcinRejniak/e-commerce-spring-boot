package com.example.ecommerce.service;

import com.example.ecommerce.dto.CountryDto;
import com.example.ecommerce.dto.StateDto;
import com.example.ecommerce.webclient.altoal.AltoalCountryClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryService {

    private final AltoalCountryClient altoalCountryClient;

    public List<CountryDto> getCountry() {
        return altoalCountryClient.getCountries();
    }

    public StateDto getStates(String country) {
        return altoalCountryClient.getStates(country);
    }
}
