package com.airbnb.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class RoomTypeDto {

    private Long id;

    private String typeOfRoom;

}