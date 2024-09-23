package com.airbnb.service;

import com.airbnb.dto.CityDto;
import com.airbnb.entity.City;

public interface CityService {
    CityDto createCityDetails(CityDto cityDto);

    City getCityId(long cityId);

    void deleteCityByName(String name);
}
