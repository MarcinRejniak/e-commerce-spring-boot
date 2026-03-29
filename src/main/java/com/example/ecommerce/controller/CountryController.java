package com.example.ecommerce.controller;

import com.example.ecommerce.dto.CountryDto;
import com.example.ecommerce.dto.StateDto;
import com.example.ecommerce.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class CountryController {

    private final CountryService countryService;

    @GetMapping("/countries")
    public List<CountryDto> getCountry() {
        return countryService.getCountry();
    }

    @GetMapping("/states")
    public StateDto getStates(@RequestParam(name = "country") String country) {
        return countryService.getStates(country);
    }
}
