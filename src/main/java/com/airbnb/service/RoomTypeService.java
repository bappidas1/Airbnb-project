package com.airbnb.service;

import com.airbnb.dto.RoomTypeDto;
import com.airbnb.entity.RoomType;

public interface RoomTypeService {
    RoomTypeDto createRoomType(RoomTypeDto roomTypeDto);



    RoomType getByRoomType(String typeOfRoom);
}
