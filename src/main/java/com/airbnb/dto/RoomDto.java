package com.airbnb.dto;

import com.airbnb.entity.Property;
import com.airbnb.entity.RoomType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter

public class RoomDto {

    private Long id;

    private Integer count;

    private Float price;

    private Property property;

//    @Column(name = "booked_rooms")
//    private Integer booked_rooms;

    private RoomType roomType;

    private LocalDate date;

}