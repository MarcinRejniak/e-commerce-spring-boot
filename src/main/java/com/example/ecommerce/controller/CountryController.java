package com.example.ecommerce.controller;

import com.example.ecommerce.dto.CountryDto;
import com.example.ecommerce.dto.StateDto;
import com.example.ecommerce.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:4200")
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
    public List<StateDto> getStates(@RequestParam(name = "country") String country) {
        return countryService.getStates(country);
    }
}
