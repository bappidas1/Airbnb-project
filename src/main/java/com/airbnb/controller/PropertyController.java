package com.airbnb.controller;

import com.airbnb.dto.PropertyDto;
import com.airbnb.entity.City;
import com.airbnb.entity.Country;
import com.airbnb.entity.Property;
import com.airbnb.service.CityService;
import com.airbnb.service.CountryService;
import com.airbnb.service.PropertyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/v1/property")
public class PropertyController {
    private PropertyService propertyService;
    private CountryService countryService;
    private CityService cityService;

    public PropertyController(PropertyService propertyService, CountryService countryService, CityService cityService) {
        this.propertyService = propertyService;
        this.countryService = countryService;
        this.cityService = cityService;
    }

    @GetMapping("/propertyResult")
    public List<PropertyDto> searchProperty(
            @RequestParam("name") String cityName)
    {
        List<PropertyDto> propertyDtos = propertyService.searchByCity(cityName);
        return propertyDtos;
    }

    @PostMapping("/addProperty")
    public ResponseEntity<PropertyDto> insertProperty(
            @RequestBody PropertyDto propertyDto,
            @RequestParam long countryId,
            @RequestParam long cityId)
    {
        Country countryId1 = countryService.getCountryId(countryId);
        City cityId1 = cityService.getCityId(cityId);
        propertyDto.setCountry(countryId1);
        propertyDto.setCity(cityId1);
        PropertyDto propertyDetails = propertyService.createPropertyDetails(propertyDto);
        return new ResponseEntity<>(propertyDetails, HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<String > deletePropertyByCityName(@RequestParam String name){
        propertyService.deletePropertyByCityName(name);
        return new ResponseEntity<>("Property Deleted", HttpStatus.OK);
    }

}
