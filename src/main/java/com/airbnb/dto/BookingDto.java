package com.airbnb.dto;

import com.airbnb.entity.AppUser;
import com.airbnb.entity.Property;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter

public class BookingDto {

    private Long id;

    private Integer noOfGuests;

    private String guestName;

    private String mobile;

    private String email;

    private String typeOfRoom;

    private Property property;

    private AppUser appUser;

    private Float total_price;

    private Integer totalNights;

    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    private Integer roomsBooked;

}