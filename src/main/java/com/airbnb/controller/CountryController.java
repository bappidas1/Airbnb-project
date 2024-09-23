package com.airbnb.controller;

import com.airbnb.dto.CountryDto;
import com.airbnb.service.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app/v1/country")
public class CountryController {
    private CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @PostMapping("/addCountry")
    public ResponseEntity<CountryDto> insertCountry(@RequestBody CountryDto countryDto){
        CountryDto countryDetails = countryService.createCountryDetails(countryDto);
        return new ResponseEntity<>(countryDetails, HttpStatus.CREATED);
    }
}
