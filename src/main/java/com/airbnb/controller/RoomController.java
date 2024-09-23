package com.airbnb.controller;


import com.airbnb.dto.RoomDto;
import com.airbnb.entity.Property;

import com.airbnb.entity.RoomType;
import com.airbnb.service.PropertyService;
import com.airbnb.service.RoomService;
import com.airbnb.service.RoomTypeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/room")
public class RoomController {
    private RoomService roomService;
    private PropertyService propertyService;
    private RoomTypeService roomTypeService;

    @PostMapping
    public ResponseEntity<RoomDto> createRoom(@RequestBody RoomDto roomDto, @RequestParam long id, @RequestParam String type_of_room){
        Property property = propertyService.getById(id);
        RoomType byId = roomTypeService.getByRoomType(type_of_room);
        roomDto.setRoomType(byId);

        roomDto.setProperty(property);
        RoomDto roomDetails = roomService.createRoomDetails(roomDto);
        return new ResponseEntity<>(roomDetails, HttpStatus.CREATED);
    }


}
