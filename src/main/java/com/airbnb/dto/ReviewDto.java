package com.airbnb.dto;

import com.airbnb.entity.AppUser;
import com.airbnb.entity.Property;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ReviewDto {

    private Long id;

    private Integer rating;

    private String description;


    private AppUser appUser;

    private Property property;

}