package com.airbnb.controller;

import com.airbnb.dto.CityDto;
import com.airbnb.service.CityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app/v1/city")
public class CityController {
    private CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @PostMapping("/addCity")
    public ResponseEntity<CityDto> insertCityDetails(@RequestBody CityDto cityDto){
        CityDto cityDetails = cityService.createCityDetails(cityDto);
        return new ResponseEntity<>(cityDetails, HttpStatus.CREATED);
    }
//    public ResponseEntity<String> deleteCityByName(@RequestParam String name){
//        cityService.deleteCityByName(name);
//    }
}
