package com.airbnb.service;

import com.airbnb.dto.CityDto;
import com.airbnb.dto.CountryDto;
import com.airbnb.entity.City;
import com.airbnb.entity.Country;
import com.airbnb.repository.CountryRepository;
import org.springframework.stereotype.Service;

@Service
public class CountryServiceImpl implements CountryService{
    private CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public CountryDto createCountryDetails(CountryDto countryDto) {
        Country countryEntity = mapToEntity(countryDto);
        Country savedEntity = countryRepository.save(countryEntity);
        CountryDto countryDto1 = mapToDto(savedEntity);
        return countryDto1;
    }

    @Override
    public Country getCountryId(long countryId) {
        Country country = countryRepository.findById(countryId).get();
        return country;
    }

    Country mapToEntity(CountryDto dto) {
        Country entity = new Country();
//        entity.setId(entity.getId());
        entity.setName(dto.getName());
        return entity;
    }

    CountryDto mapToDto(Country entity) {
        CountryDto dto = new CountryDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }
}
