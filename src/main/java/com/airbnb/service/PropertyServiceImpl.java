package com.airbnb.service;

import com.airbnb.dto.PropertyDto;
import com.airbnb.entity.Property;
import com.airbnb.exception.ResourceNotFound;
import com.airbnb.repository.PropertyRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PropertyServiceImpl implements PropertyService{
    private PropertyRepository propertyRepository;


    public PropertyServiceImpl(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    @Override
    public PropertyDto createPropertyDetails(PropertyDto propertyDto) {
        Property propertyEntity = mapToEntity(propertyDto);
        Property savedProperty = propertyRepository.save(propertyEntity);
        PropertyDto propertyDto1 = mapToDto(savedProperty);
        return propertyDto1;
    }

    @Override
    public List<PropertyDto> searchByCity(String cityName) {
        List<Property> properties = propertyRepository.searchProperty(cityName);
        List<PropertyDto> collect = properties.stream().map(p -> mapToDto(p)).collect(Collectors.toList());
        return collect;
    }

    @Override
    @Transactional
    public void deletePropertyByCityName(String name) {
        propertyRepository.deleteByCityName(name);

    }

    @Override
    public Property getById(long propertyId) {
        Property property = propertyRepository.findById(propertyId).orElseThrow(
                () -> new ResourceNotFound("The given property id is not found" + propertyId)
        );

        return property;
    }

    Property mapToEntity(PropertyDto dto) {
        Property entity = new Property();
        entity.setName(dto.getName());
        entity.setNumberOfGuests(dto.getNumberOfGuests());
        entity.setNumberOfBeds(dto.getNumberOfBeds());
        entity.setNumberOfBedrooms(dto.getNumberOfBedrooms());
        entity.setNumberOfBathrooms(dto.getNumberOfBathrooms());
        entity.setCountry(dto.getCountry());
        entity.setCity(dto.getCity());
        return entity;
    }

    PropertyDto mapToDto(Property entity) {
        PropertyDto dto = new PropertyDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setNumberOfGuests(entity.getNumberOfGuests());
        dto.setNumberOfBeds(entity.getNumberOfBeds());
        dto.setNumberOfBedrooms(entity.getNumberOfBedrooms());
        dto.setNumberOfBathrooms(entity.getNumberOfBathrooms());
        dto.setCountry(entity.getCountry());
        dto.setCity(entity.getCity());
        return dto;
    }
}
