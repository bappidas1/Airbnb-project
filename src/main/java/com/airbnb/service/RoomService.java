package com.airbnb.service;


import com.airbnb.dto.RoomDto;
import com.airbnb.entity.Room;

import java.time.LocalDate;

public interface RoomService {


    RoomDto createRoomDetails(RoomDto roomDto);

    Room getByPropertyIdAndType(long propertyId, String roomType, LocalDate date);

    Room saveRoom(Room room);

    String updateRoom(Room room);
}
