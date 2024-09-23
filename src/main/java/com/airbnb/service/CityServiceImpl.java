package com.airbnb.service;


import com.airbnb.dto.CityDto;

import com.airbnb.entity.City;
import com.airbnb.repository.CityRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CityServiceImpl implements CityService {

    private CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public CityDto createCityDetails(CityDto cityDto) {
        City cityEntity = mapToEntity(cityDto);
        City savedCity = cityRepository.save(cityEntity);
        CityDto cityDto1 = mapToDto(savedCity);
        return cityDto1;
    }

    @Override
    public City getCityId(long cityId) {
        City city = cityRepository.findById(cityId).get();
        return city;
    }

    @Override
    public void deleteCityByName(String name) {

    }

    City mapToEntity(CityDto dto) {
        City entity = new City();
//        entity.setId(entity.getId());
        entity.setName(dto.getName());
        return entity;
    }

    CityDto mapToDto(City entity) {
        CityDto dto = new CityDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }
}
