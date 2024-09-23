package com.airbnb.dto;

import com.airbnb.entity.City;
import com.airbnb.entity.Country;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class PropertyDto {

    private Long id;

    private String name;

    private Integer numberOfGuests;

    private Integer numberOfBeds;

    private String numberOfBedrooms;

    private String numberOfBathrooms;

    private Country country;

    private City city;

}