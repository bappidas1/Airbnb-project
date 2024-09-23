package com.airbnb.service;

import com.airbnb.dto.CountryDto;
import com.airbnb.entity.Country;

public interface CountryService {
    CountryDto createCountryDetails(CountryDto countryDto);

    Country getCountryId(long countryId);
}
