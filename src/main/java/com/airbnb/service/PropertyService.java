package com.airbnb.service;

import com.airbnb.dto.PropertyDto;
import com.airbnb.entity.Property;

import java.util.List;

public interface PropertyService {
    PropertyDto createPropertyDetails(PropertyDto propertyDto);

    List<PropertyDto> searchByCity(String cityName);

    void deletePropertyByCityName(String name);

    Property getById(long propertyId);
}
